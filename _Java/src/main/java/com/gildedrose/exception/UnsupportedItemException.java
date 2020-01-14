package com.gildedrose.exception;

/**
 * {@link UnsupportedItemException} is {@link RuntimeException}, that should be thrown if an invalid
 * {@link com.gildedrose.Item} is encountered.
 * An invalid Item is an Item, which is null, or has a null name.
 */
public class UnsupportedItemException extends RuntimeException {

    private static final long serialVersionUID = -8460356990632230194L;

    /**
     * Constructor for UnsupportedItemException, which expects a message.
     * @param message The message describing the exception
     */
    public UnsupportedItemException(String message) {
        super(message);
    }
}
