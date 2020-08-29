package com.retheviper.excel.worker;

import com.retheviper.excel.definition.CellDef;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * Worker for manipulate cell.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class CellWorker {

    /**
     * Definition of cell.
     */
    private final CellDef cellDef;

    /**
     * Apache POI's cell class.
     */
    private final Cell cell;

    /**
     * Format for date.
     */
    private final String dateFormat = "yyyy/MM/dd";

    /**
     * Read cell's value and write into object's field.
     *
     * @param object
     * @param <T>
     * @return
     */
    public <T> T cellToObj(final T object) {
        final Field field = getObjectField(object);
        try {
            switch (cellDef.getType()) {
                case INTEGER:
                    field.set(object, (int) this.cell.getNumericCellValue());
                    break;
                case LONG:
                    field.set(object, (long) this.cell.getNumericCellValue());
                    break;
                case DOUBLE:
                    field.set(object, this.cell.getNumericCellValue());
                    break;
                case STRING:
                    field.set(object, this.cell.getStringCellValue() != "" ? this.cell.getStringCellValue() : null);
                    break;
                case BOOLEAN:
                    field.set(object, this.cell.getBooleanCellValue());
                    break;
                case LOCALDATETIME:
                    field.set(object, this.cell.getLocalDateTimeCellValue());
                    break;
                case DATE:
                    try {
                        field.set(object, this.cell.getDateCellValue());
                    } catch (IllegalStateException e) {
                        if (e.getMessage().contains("STRING")) {
                            field.set(object, new SimpleDateFormat(dateFormat).parse(this.cell.getStringCellValue()));
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            return null;
        }
        return object;
    }

    /**
     * Read object's field value and write into cell's field.
     *
     * @param object
     * @param <T>
     * @return
     */
    public <T> void objToCell(final T object) {
        final Field field = getObjectField(object);
        try {
            final Object objectValue = field.get(object);
            switch (cellDef.getType()) {
                case INTEGER:
                    this.cell.setCellValue((int) objectValue);
                    break;
                case LONG:
                    this.cell.setCellValue((long) objectValue);
                    break;
                case DOUBLE:
                    this.cell.setCellValue((double) objectValue);
                    break;
                case STRING:
                    this.cell.setCellValue((String) objectValue);
                    break;
                case BOOLEAN:
                    this.cell.setCellValue((boolean) objectValue);
                    break;
                case LOCALDATETIME:
                    this.cell.setCellValue((LocalDateTime) objectValue);
                    break;
                case DATE:
                    this.cell.setCellValue(new SimpleDateFormat(dateFormat).format(objectValue));
                    break;
            }
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Get field from object.
     *
     * @param object
     * @param <T>
     * @return
     */
    private <T> Field getObjectField(final T object) {
        try {
            final Field field = object.getClass().getDeclaredField(this.cellDef.getName());
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
