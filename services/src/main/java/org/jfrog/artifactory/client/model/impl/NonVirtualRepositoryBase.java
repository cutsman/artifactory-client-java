package org.jfrog.artifactory.client.model.impl;

import org.jfrog.artifactory.client.model.NonVirtualRepository;
import org.jfrog.artifactory.client.model.repository.settings.RepositorySettings;

import java.util.List;

/**
 * @author jbaruch
 * @since 29/07/12
 */
public abstract class NonVirtualRepositoryBase extends RepositoryBase implements NonVirtualRepository {

    private boolean blackedOut;
    private List<String> propertySets;
    protected boolean archiveBrowsingEnabled;

    protected NonVirtualRepositoryBase() {
    }

    protected NonVirtualRepositoryBase(String key, RepositorySettings settings,
        String description, String excludesPattern, String includesPattern,
        String notes, boolean blackedOut,
        List<String> propertySets,
        String repoLayoutRef,
        boolean archiveBrowsingEnabled) {

        super(key, settings, description, excludesPattern, includesPattern, notes,
            repoLayoutRef);

        this.blackedOut = blackedOut;
        this.propertySets = propertySets;
        this.archiveBrowsingEnabled = archiveBrowsingEnabled;
    }

    @Override
    public boolean isBlackedOut() {
        return blackedOut;
    }

    private void setBlackedOut(boolean blackedOut) {
        this.blackedOut = blackedOut;
    }

    @Override
    public List<String> getPropertySets() {
        return propertySets;
    }

    private void setPropertySets(List<String> propertySets) {
        this.propertySets = propertySets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        NonVirtualRepositoryBase that = (NonVirtualRepositoryBase) o;

        if (blackedOut != that.blackedOut) {
            return false;
        }
        if (propertySets != null ? !propertySets.equals(that.propertySets) : that.propertySets != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (blackedOut ? 1 : 0);
        result = 31 * result + (propertySets != null ? propertySets.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NonVirtualRepositoryBase{" +
                "blackedOut=" + blackedOut +
                ", propertySets=" + propertySets +
                '}';
    }

    @Override
    public boolean isArchiveBrowsingEnabled() {
        return archiveBrowsingEnabled;
    }

    private void setArchiveBrowsingEnabled(boolean archiveBrowsingEnabled) {
        this.archiveBrowsingEnabled = archiveBrowsingEnabled;
    }
}
