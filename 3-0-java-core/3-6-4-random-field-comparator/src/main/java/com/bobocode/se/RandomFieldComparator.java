package com.bobocode.se;

import java.lang.reflect.Field;
import java.util.Comparator;

/**
 * A generic comparator that is comparing a random field of the given class. The field is either primitive or
 * {@link Comparable}. It is chosen during comparator instance creation and is used for all comparisons.
 * <p>
 * If no field is available to compare, the constructor throws {@link IllegalArgumentException}
 *
 * @param <T> the type of the objects that may be compared by this comparator
 *<p><p>
 *  <strong>TODO: Completed Exercise </strong>
 *  <p>
 *
 * @author Stanislav Zabramnyi
 */
public class RandomFieldComparator<T> implements Comparator<T> {

    private Field comparingField;

    public RandomFieldComparator(Class<T> targetType) {
        this.comparingField = chooseRandomComparableField(targetType);
        if (comparingField == null) {
            throw new IllegalArgumentException("No comparable fields found in " + targetType);
        }
    }

    /**
     * Compares two objects of the class T by the value of the field that was randomly chosen. It allows null values
     * for the fields, and it treats null value greater than a non-null value.
     *
     * @param o1
     * @param o2
     * @return positive int in case of first parameter {@param o1} is greater than second one {@param o2},
     *         zero if objects are equals,
     *         negative int in case of first parameter {@param o1} is less than second one {@param o2}.
     */
    @Override
    public int compare(T o1, T o2) {
        comparingField.setAccessible(true);

        try {
            Object fieldValue1 = comparingField.get(o1);
            Object fieldValue2 = comparingField.get(o2);

            // Handle null values (null is considered greater)
            if (fieldValue1 == null) {
                return fieldValue2 == null ? 0 : 1;
            } else if (fieldValue2 == null) {
                return -1;
            }

            // Use comparable interface for comparison
            if (fieldValue1 instanceof Comparable) {
                return ((Comparable) fieldValue1).compareTo(fieldValue2);
            } else {
                throw new IllegalStateException("Unexpected field type: " + comparingField.getType());
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to access field: " + comparingField.getName(), e);
        }
    }

    /**
     * Returns the name of the randomly-chosen comparing field.
     */
    public String getComparingFieldName() {
        return comparingField.getName();
    }

    /**
     * Returns a statement "Random field comparator of class '%s' is comparing '%s'" where the first param is the name
     * of the type T, and the second parameter is the comparing field name.
     *
     * @return a predefined statement
     */
    @Override
    public String toString() {
        return String.format("Random field comparator of class '%s' is comparing '%s'",
                comparingField.getDeclaringClass().getSimpleName(), getComparingFieldName());
    }

    private Field chooseRandomComparableField(Class<T> targetType) {
        Field[] declaredFields = targetType.getDeclaredFields();
        for (Field field : declaredFields) {
            if (isPrimitiveOrComparable(field.getType())) {
                field.setAccessible(true);  // Allow access to private fields
                return field;
            }
        }
        return null;
    }

    private boolean isPrimitiveOrComparable(Class<?> type) {
        return type.isPrimitive() || Comparable.class.isAssignableFrom(type);
    }
}
