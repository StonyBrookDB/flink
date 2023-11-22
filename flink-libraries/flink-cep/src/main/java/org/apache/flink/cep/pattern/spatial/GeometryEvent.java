/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.cep.pattern.spatial;

import org.locationtech.jts.geom.Geometry;

import java.util.Optional;

/** Base class for all events with geometry info. */
public abstract class GeometryEvent {

    private Optional<Geometry> geometry;

    public GeometryEvent() {
        this.geometry = Optional.empty();
    }

    public GeometryEvent(Geometry geometry) {
        this.geometry = Optional.of(geometry);
    }

    public boolean hasGeometry() {
        return this.geometry.isPresent();
    }

    public Geometry getGeometry() throws Exception {
        if (!this.hasGeometry()) {
            throw new Exception("No geometry event present");
        }
        return this.geometry.get();
    }
}
