/*
 * This file is generated by jOOQ.
 */
package generated.com.moneytree.persist.db.generated.tables.daos;


import generated.com.moneytree.persist.db.generated.tables.Metadata;
import generated.com.moneytree.persist.db.generated.tables.records.MetadataRecord;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.13.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MetadataDao extends DAOImpl<MetadataRecord, generated.com.moneytree.persist.db.generated.tables.pojos.Metadata, Long> {

    /**
     * Create a new MetadataDao without any configuration
     */
    public MetadataDao() {
        super(Metadata.METADATA, generated.com.moneytree.persist.db.generated.tables.pojos.Metadata.class);
    }

    /**
     * Create a new MetadataDao with an attached configuration
     */
    public MetadataDao(Configuration configuration) {
        super(Metadata.METADATA, generated.com.moneytree.persist.db.generated.tables.pojos.Metadata.class, configuration);
    }

    @Override
    public Long getId(generated.com.moneytree.persist.db.generated.tables.pojos.Metadata object) {
        return object.getMetadataId();
    }

    /**
     * Fetch records that have <code>metadata_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<generated.com.moneytree.persist.db.generated.tables.pojos.Metadata> fetchRangeOfMetadataId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Metadata.METADATA.METADATA_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>metadata_id IN (values)</code>
     */
    public List<generated.com.moneytree.persist.db.generated.tables.pojos.Metadata> fetchByMetadataId(Long... values) {
        return fetch(Metadata.METADATA.METADATA_ID, values);
    }

    /**
     * Fetch a unique record that has <code>metadata_id = value</code>
     */
    public generated.com.moneytree.persist.db.generated.tables.pojos.Metadata fetchOneByMetadataId(Long value) {
        return fetchOne(Metadata.METADATA.METADATA_ID, value);
    }

    /**
     * Fetch records that have <code>date_created BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<generated.com.moneytree.persist.db.generated.tables.pojos.Metadata> fetchRangeOfDateCreated(Timestamp lowerInclusive, Timestamp upperInclusive) {
        return fetchRange(Metadata.METADATA.DATE_CREATED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>date_created IN (values)</code>
     */
    public List<generated.com.moneytree.persist.db.generated.tables.pojos.Metadata> fetchByDateCreated(Timestamp... values) {
        return fetch(Metadata.METADATA.DATE_CREATED, values);
    }

    /**
     * Fetch records that have <code>date_modified BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<generated.com.moneytree.persist.db.generated.tables.pojos.Metadata> fetchRangeOfDateModified(Timestamp lowerInclusive, Timestamp upperInclusive) {
        return fetchRange(Metadata.METADATA.DATE_MODIFIED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>date_modified IN (values)</code>
     */
    public List<generated.com.moneytree.persist.db.generated.tables.pojos.Metadata> fetchByDateModified(Timestamp... values) {
        return fetch(Metadata.METADATA.DATE_MODIFIED, values);
    }

    /**
     * Fetch records that have <code>notes BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<generated.com.moneytree.persist.db.generated.tables.pojos.Metadata> fetchRangeOfNotes(String lowerInclusive, String upperInclusive) {
        return fetchRange(Metadata.METADATA.NOTES, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>notes IN (values)</code>
     */
    public List<generated.com.moneytree.persist.db.generated.tables.pojos.Metadata> fetchByNotes(String... values) {
        return fetch(Metadata.METADATA.NOTES, values);
    }
}
