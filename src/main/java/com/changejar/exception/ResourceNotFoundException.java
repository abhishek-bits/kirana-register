package com.changejar.exception;

import com.changejar.enums.ResourceType;

/**
 * This exception helps us to terminate the instance on invalid requests.
 *
 * @author Abhishek Sharma
 */
public class ResourceNotFoundException extends RuntimeException {

    private ResourceType resourceType;
    private Long resourceId;

    public ResourceNotFoundException(ResourceType resourceType, Long resourceId) {
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }

    public String getMessage() {
        return resourceType + " with ID: " + resourceId + " not found.";
    }
}
