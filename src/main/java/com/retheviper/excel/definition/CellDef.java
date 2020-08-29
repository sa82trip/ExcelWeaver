package com.retheviper.excel.definition;

import com.retheviper.excel.definition.type.DataType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Definition of one cell.
 */
@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class CellDef {

    /**
     * Cell's name.
     */
    private final String name;

    /**
     * Cell's data type.
     */
    private final DataType type;

    /**
     * Cell's index. (Horizontal)
     */
    private final int cellIndex;
}
