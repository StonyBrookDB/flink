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

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.util.GeometricShapeFactory;

/** Base class for all events with circle info. */
public abstract class CircleEvent extends GeometryEvent {
    private Double centreX;
    private Double centreY;
    private Double radius;

    public CircleEvent() {}

    public CircleEvent(Double centreX, Double centreY, Double radius) {
        this.centreX = centreX;
        this.centreY = centreY;
        this.radius = radius;
    }

    public Double getCentreX() {
        return centreX;
    }

    public Double getCentreY() {
        return centreY;
    }

    public Double getRadius() {
        return radius;
    }

    public void setCentreX(Double centreX) {
        this.centreX = centreX;
    }

    public void setCentreY(Double centreY) {
        this.centreY = centreY;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    public Geometry getGeometry() {
        GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
        shapeFactory.setNumPoints(128);
        shapeFactory.setCentre(new Coordinate(centreX, centreY));
        shapeFactory.setHeight(radius / 111320d);
        shapeFactory.setWidth(radius / (40075000 * Math.cos(Math.toRadians(centreX)) / 360));
        Polygon circle = shapeFactory.createEllipse();
        return circle;
    }
}
