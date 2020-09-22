package com.Lesson3.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class ArrayWrapper {
    @JsonProperty("array")
    private int[] array;

    public ArrayWrapper() {
        super();
    }

    public ArrayWrapper(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return "ArrayWrapper{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
