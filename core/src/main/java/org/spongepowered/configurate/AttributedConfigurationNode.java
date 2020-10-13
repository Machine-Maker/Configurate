/*
 * Configurate
 * Copyright (C) zml and Configurate contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.spongepowered.configurate;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Map;
import java.util.function.Consumer;

/**
 * A configuration node that can have both comments and attributes attached.
 */
public interface AttributedConfigurationNode extends CommentedConfigurationNodeIntermediary<AttributedConfigurationNode> {

    /**
     * The default tag name for a root node.
     */
    String TAG_ROOT = "root";

    /**
     * Create a new factory providing {@link AttributedConfigurationNode} instances.
     *
     * <p>The returned factory will create nodes with default options and the
     * tag name {@code root}.</p>
     *
     * @return a new factory
     */
    static ConfigurationNodeFactory<AttributedConfigurationNode> factory() {
        return options -> AttributedConfigurationNode.root(TAG_ROOT, options);
    }

    /**
     * Create a new root node with the {@link #TAG_ROOT default} tag name.
     *
     * @return a new empty node
     */
    static AttributedConfigurationNode root() {
        return root(TAG_ROOT, ConfigurationOptions.defaults());
    }

    /**
     * Create a new root node with {@link #TAG_ROOT default} tag name
     * and default options.
     *
     * <p>{@code action} will be applied to the new new node.</p>
     *
     * @param action action to perform
     * @return a new empty node
     */
    static AttributedConfigurationNode root(final Consumer<? super AttributedConfigurationNode> action) {
        return root().act(action);
    }

    /**
     * Create a new root node with the provided tag name but default options.
     *
     * <p>A root node is always attached, and has no parent and an
     * empty path.</p>
     *
     * @param tagName node's tag name
     * @return a new empty node
     */
    static AttributedConfigurationNode root(final String tagName) {
        return root(tagName, ConfigurationOptions.defaults());
    }

    /**
     * Create a new root node with the provided tag name and default options.
     *
     * <p>The node will be initialized with the {@code action}.</p>
     *
     * <p>A root node is always attached, and has no parent and an
     * empty path.</p>
     *
     * @param tagName node's tag name
     * @param action initialization action
     * @return a new empty node
     */
    static AttributedConfigurationNode root(final String tagName, final Consumer<? super AttributedConfigurationNode> action) {
        return root(tagName).act(action);
    }

    /**
     * Create a new root node with the provided tag name and options.
     *
     * <p>A root node is always attached, and has no parent and an
     * empty path.</p>
     *
     * @param tagName node's tag name
     * @param options options to apply
     * @return a new empty node
     */
    static AttributedConfigurationNode root(final String tagName, final ConfigurationOptions options) {
        return new AttributedConfigurationNodeImpl(tagName, null, null, options);
    }

    /**
     * Create a new root node with the provided tag name, options
     * and initializer.
     *
     * <p>A root node is always attached, and has no parent and an
     * empty path.</p>
     *
     * @param tagName node's tag name
     * @param options options to apply
     * @param action action to initialize the node with
     * @return a new initialized node
     */
    static AttributedConfigurationNode root(final String tagName, final ConfigurationOptions options,
            Consumer<? super AttributedConfigurationNode> action) {
        return root(tagName, options).act(action);
    }

    /**
     * Gets the tag name of this node.
     *
     * @return the tag name
     */
    String tagName();

    /**
     * Sets the tag name of this node.
     *
     * <p>Will have no effect when called on nodes which are direct values of a
     * {@link #childrenMap() child map}, as the corresponding key is used as
     * the tag name.</p>
     *
     * @param name the name to set, cannot be null
     * @return this node
     */
    AttributedConfigurationNode tagName(String name);

    /**
     * Adds an attribute to this node.
     *
     * @param name the name of the attribute
     * @param value the value of the attribute
     * @return this node
     */
    AttributedConfigurationNode addAttribute(String name, String value);

    /**
     * Removes an attribute from this node.
     *
     * @param name the name of the attribute to remove
     * @return this node
     */
    AttributedConfigurationNode removeAttribute(String name);

    /**
     * Sets the attributes of this node.
     *
     * @param attributes the attributes to set
     * @return this node
     */
    AttributedConfigurationNode attributes(Map<String, String> attributes);

    /**
     * Gets the attributes this node has.
     *
     * <p>The returned map is immutable.</p>
     *
     * @return the map of attributes
     */
    Map<String, String> attributes();

    /**
     * Gets if this node has any attributes.
     *
     * @return true if this node has any attributes
     */
    boolean hasAttributes();

    /**
     * Gets the value of an attribute, or null if this node doesn't have the
     * given attribute.
     *
     * @param name the name of the attribute to get
     * @return this node
     */
    @Nullable String attribute(String name);

}
