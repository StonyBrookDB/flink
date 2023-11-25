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

import java.io.Serializable;

/** Base class for all events with ellipse info. */
public abstract class EllipseEvent extends GeometryEvent implements Serializable {

    private static final long serialVersionUID = 4L;

    private Double centreX;
    private Double centreY;
    private Double height;
    private Double width;

    public EllipseEvent() {}

    public EllipseEvent(Double centreX, Double centreY, Double height, Double width) {
        this.centreX = centreX;
        this.centreY = centreY;
        this.height = height;
        this.width = width;
    }

    public Double getCentreX() {
        return centreX;
    }

    public Double getCentreY() {
        return centreY;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    public void setCentreX(Double centreX) {
        this.centreX = centreX;
    }

    public void setCentreY(Double centreY) {
        this.centreY = centreY;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    @Override
    public Geometry getGeometry() {
        GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
        shapeFactory.setNumPoints(128);
        shapeFactory.setCentre(new Coordinate(centreX, centreY));
        shapeFactory.setHeight(height);
        shapeFactory.setWidth(width);
        Polygon ellipse = shapeFactory.createEllipse();
        return ellipse;
    }
}
