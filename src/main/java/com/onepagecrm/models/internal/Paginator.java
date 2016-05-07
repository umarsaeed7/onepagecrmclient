package com.onepagecrm.models.internal;

import com.onepagecrm.exceptions.NoMorePagesException;
import com.onepagecrm.models.BaseResource;
import com.onepagecrm.models.serializer.RequestMetadataSerializer;

import java.io.Serializable;

public class Paginator extends BaseResource implements Serializable {

    private Integer currentPage;
    private Integer perPage;
    private Integer maxPage;
    private Integer totalCount;

    public Integer getNextPageNo() throws NoMorePagesException {
        if (currentPage < maxPage) {
            currentPage++;
            return currentPage;
        } else {
            throw new NoMorePagesException("Already at the end of the pages.")
                    .setErrorMessage("Already at the end of the pages.");
        }
    }

    public Integer getPreviousPageNo() throws NoMorePagesException {
        if (currentPage > 1) {
            currentPage--;
            return currentPage;
        } else {
            throw new NoMorePagesException("Already at the start of the pages.")
                    .setErrorMessage("Already at the start of the pages.");
        }
    }

    public Paginator() {
        // Set defaults.
        this.currentPage = 1;
        this.perPage = 10;
        this.maxPage = 1;
    }

    public Paginator reset() {
        // Restore defaults.
        this.currentPage = 1;
        this.perPage = 10;
        this.maxPage = 1;
        return this;
    }

    @Override
    public String toString() {
        return RequestMetadataSerializer.toJsonKeyValuePair(this);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Paginator) {
            Paginator toCompare = (Paginator) object;
            if (this.currentPage != null && toCompare.currentPage != null) {
                return this.currentPage.equals(toCompare.currentPage);
            }
        }
        return false;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Paginator setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public Paginator setPerPage(Integer perPage) {
        this.perPage = perPage;
        return this;
    }

    public Integer getMaxPage() {
        return maxPage;
    }

    public Paginator setMaxPage(Integer maxPage) {
        this.maxPage = maxPage;
        return this;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public Paginator setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }
}
