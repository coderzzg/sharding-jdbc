/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingjdbc.core.parsing.parser.context.condition;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * And conditions.
 *
 * @author maxiaoguang
 */
@RequiredArgsConstructor
@Getter
@ToString
public final class AndCondition {
    
    private final List<Condition> conditions = new LinkedList<>();
    
    /**
     * Get conditions map.
     * 
     * @return conditions map
     */
    public Map<Column, List<Condition>> getConditionsMap() {
        Map<Column, List<Condition>> result = new LinkedHashMap<>(conditions.size(), 1);
        for (Condition each : conditions) {
            if (!result.containsKey(each.getColumn())) {
                result.put(each.getColumn(), new LinkedList<Condition>());
            }
            result.get(each.getColumn()).add(each);
        }
        return result;
    }
    
    /**
     * Find condition via column.
     *
     * @param column column
     * @return found condition
     * @deprecated only test call
     */
    @Deprecated
    public Optional<Condition> find(final Column column) {
        Condition result = null;
        for (Condition each : conditions) {
            if (Objects.equal(each.getColumn(), column)) {
                result = each;
            }
        }
        return Optional.fromNullable(result);
    }
}
