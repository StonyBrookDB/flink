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

package org.apache.flink.cep.pattern;

import org.apache.flink.cep.nfa.NFA;
import org.apache.flink.cep.nfa.aftermatch.AfterMatchSkipStrategy;
import org.apache.flink.cep.pattern.spatial.GeometryEvent;

/**
 * Base class for a spatial pattern definition.
 *
 * <p>A pattern definition is used by {@link org.apache.flink.cep.nfa.compiler.NFACompiler} to
 * create a {@link NFA}.
 *
 * <pre>{@code
 * SpatialPattern<T, F> pattern = SpatialPattern.<T>begin("start")
 *   .next("middle").subtype(F.class)
 *   .followedBy("end").where(new MySpatialCondition());
 * }</pre>
 *
 * @param <T> Base type of the elements appearing in the pattern
 * @param <F> Subtype of T to which the current pattern operator is constrained
 */
public class SpatialPattern<T extends GeometryEvent, F extends T> extends Pattern<T, F> {

    protected SpatialPattern(
            String name,
            Pattern<T, ? extends T> previous,
            Quantifier.ConsumingStrategy consumingStrategy,
            AfterMatchSkipStrategy afterMatchSkipStrategy) {
        super(name, previous, consumingStrategy, afterMatchSkipStrategy);
    }
}
