/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.wildfly.swarm.config.generator.model;

import org.jboss.dmr.ModelNode;
import org.jboss.dmr.Property;
import org.wildfly.swarm.config.runtime.model.ResourceAddress;

import java.util.Collection;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.*;

/**
 * @author Harald Pehl
 */
public class Operation extends ModelNode {

    public static class Builder {

        private final String name;
        private final ResourceAddress address;
        private final ModelNode parameter;
        private String role;

        public Builder(final String name, final ResourceAddress address) {
            this.address = address;
            this.name = name;
            this.parameter = new ModelNode();
        }

        public Builder param(String name, boolean value) {
            parameter.get(name).set(value);
            return this;
        }

        public Builder param(String name, int value) {
            parameter.get(name).set(value);
            return this;
        }

        public Builder param(String name, long value) {
            parameter.get(name).set(value);
            return this;
        }

        public Builder param(String name, double value) {
            parameter.get(name).set(value);
            return this;
        }

        public Builder param(String name, String value) {
            parameter.get(name).set(value);
            return this;
        }

        public Builder param(String name, ModelNode value) {
            parameter.get(name).set(value);
            return this;
        }

        public Builder param(String name, Collection<ModelNode> value) {
            parameter.get(name).set(value);
            return this;
        }

        public Builder runAs(String role) {
            this.role = role;
            return this;
        }

        public Operation build() {
            return new Operation(name, address, parameter, role);
        }
    }


    protected Operation(final String name, final ResourceAddress address, final ModelNode parameter, final String role) {
        get(OP).set(name);
        get(ADDRESS).set(address);
        if (parameter.isDefined()) {
            for (Property property : parameter.asPropertyList()) {
                get(property.getName()).set(property.getValue());
            }
        }
        if (role != null && !name.equals("WHOMAI")) {
            // otherwise we resolve the replacement role
            get(OPERATION_HEADERS).get(ROLES).set(role);
        }
    }
}
