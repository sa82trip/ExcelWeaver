package com.retheviper.excel.definition;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Definition of Row, which means 'Header'.
 */
@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class RowDef {

    /**
     * Collection of each header cells.
     */
    private final List<CellDef> cellDefs;
}
