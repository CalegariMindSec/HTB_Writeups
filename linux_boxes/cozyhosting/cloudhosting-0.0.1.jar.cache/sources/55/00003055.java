package org.hibernate.cfg;

import org.hibernate.Incubating;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/cfg/AvailableSettings.class */
public interface AvailableSettings {
    public static final String JAKARTA_PERSISTENCE_PROVIDER = "jakarta.persistence.provider";
    public static final String JAKARTA_TRANSACTION_TYPE = "jakarta.persistence.transactionType";
    public static final String JAKARTA_JTA_DATASOURCE = "jakarta.persistence.jtaDataSource";
    public static final String JAKARTA_NON_JTA_DATASOURCE = "jakarta.persistence.nonJtaDataSource";
    public static final String JAKARTA_JDBC_DRIVER = "jakarta.persistence.jdbc.driver";
    public static final String JAKARTA_JDBC_URL = "jakarta.persistence.jdbc.url";
    public static final String JAKARTA_JDBC_USER = "jakarta.persistence.jdbc.user";
    public static final String JAKARTA_JDBC_PASSWORD = "jakarta.persistence.jdbc.password";
    public static final String JAKARTA_SHARED_CACHE_MODE = "jakarta.persistence.sharedCache.mode";
    public static final String JAKARTA_SHARED_CACHE_RETRIEVE_MODE = "jakarta.persistence.cache.retrieveMode";
    public static final String JAKARTA_SHARED_CACHE_STORE_MODE = "jakarta.persistence.cache.storeMode";
    public static final String JAKARTA_VALIDATION_MODE = "jakarta.persistence.validation.mode";
    public static final String JAKARTA_VALIDATION_FACTORY = "jakarta.persistence.validation.factory";
    public static final String JAKARTA_PERSIST_VALIDATION_GROUP = "jakarta.persistence.validation.group.pre-persist";
    public static final String JAKARTA_UPDATE_VALIDATION_GROUP = "jakarta.persistence.validation.group.pre-update";
    public static final String JAKARTA_REMOVE_VALIDATION_GROUP = "jakarta.persistence.validation.group.pre-remove";
    public static final String JAKARTA_LOCK_SCOPE = "jakarta.persistence.lock.scope";
    public static final String JAKARTA_LOCK_TIMEOUT = "jakarta.persistence.lock.timeout";
    public static final String JAKARTA_CDI_BEAN_MANAGER = "jakarta.persistence.bean.manager";
    public static final String CLASSLOADERS = "hibernate.classLoaders";
    public static final String TC_CLASSLOADER = "hibernate.classLoader.tccl_lookup_precedence";
    public static final String JPA_METAMODEL_POPULATION = "hibernate.jpa.metamodel.population";
    public static final String STATIC_METAMODEL_POPULATION = "hibernate.jpa.static_metamodel.population";
    public static final String CONNECTION_PROVIDER = "hibernate.connection.provider_class";
    public static final String DRIVER = "hibernate.connection.driver_class";
    public static final String URL = "hibernate.connection.url";
    public static final String USER = "hibernate.connection.username";
    public static final String PASS = "hibernate.connection.password";
    public static final String ISOLATION = "hibernate.connection.isolation";
    public static final String AUTOCOMMIT = "hibernate.connection.autocommit";
    public static final String POOL_SIZE = "hibernate.connection.pool_size";
    public static final String DATASOURCE = "hibernate.connection.datasource";
    public static final String CONNECTION_PROVIDER_DISABLES_AUTOCOMMIT = "hibernate.connection.provider_disables_autocommit";
    public static final String CONNECTION_PREFIX = "hibernate.connection";
    public static final String JNDI_CLASS = "hibernate.jndi.class";
    public static final String JNDI_URL = "hibernate.jndi.url";
    public static final String JNDI_PREFIX = "hibernate.jndi";
    public static final String DIALECT = "hibernate.dialect";
    public static final String DIALECT_RESOLVERS = "hibernate.dialect_resolvers";
    @Deprecated
    public static final String DIALECT_DB_NAME = "javax.persistence.database-product-name";
    @Deprecated
    public static final String DIALECT_DB_VERSION = "javax.persistence.database-product-version";
    @Deprecated
    public static final String DIALECT_DB_MAJOR_VERSION = "javax.persistence.database-major-version";
    @Deprecated
    public static final String DIALECT_DB_MINOR_VERSION = "javax.persistence.database-minor-version";
    public static final String STORAGE_ENGINE = "hibernate.dialect.storage_engine";
    public static final String SCHEMA_MANAGEMENT_TOOL = "hibernate.schema_management_tool";
    public static final String TRANSACTION_COORDINATOR_STRATEGY = "hibernate.transaction.coordinator_class";
    public static final String JTA_PLATFORM = "hibernate.transaction.jta.platform";
    public static final String PREFER_USER_TRANSACTION = "hibernate.jta.prefer_user_transaction";
    public static final String JTA_PLATFORM_RESOLVER = "hibernate.transaction.jta.platform_resolver";
    public static final String JTA_CACHE_TM = "hibernate.jta.cacheTransactionManager";
    public static final String JTA_CACHE_UT = "hibernate.jta.cacheUserTransaction";
    public static final String DEFAULT_CATALOG = "hibernate.default_catalog";
    public static final String DEFAULT_SCHEMA = "hibernate.default_schema";
    public static final String DEFAULT_CACHE_CONCURRENCY_STRATEGY = "hibernate.cache.default_cache_concurrency_strategy";
    public static final String FORCE_DISCRIMINATOR_IN_SELECTS_BY_DEFAULT = "hibernate.discriminator.force_in_select";
    public static final String IMPLICIT_DISCRIMINATOR_COLUMNS_FOR_JOINED_SUBCLASS = "hibernate.discriminator.implicit_for_joined";
    public static final String IGNORE_EXPLICIT_DISCRIMINATOR_COLUMNS_FOR_JOINED_SUBCLASS = "hibernate.discriminator.ignore_explicit_for_joined";
    public static final String USE_NATIONALIZED_CHARACTER_DATA = "hibernate.use_nationalized_character_data";
    public static final String SCANNER = "hibernate.archive.scanner";
    public static final String SCANNER_ARCHIVE_INTERPRETER = "hibernate.archive.interpreter";
    public static final String SCANNER_DISCOVERY = "hibernate.archive.autodetection";
    public static final String IMPLICIT_NAMING_STRATEGY = "hibernate.implicit_naming_strategy";
    public static final String PHYSICAL_NAMING_STRATEGY = "hibernate.physical_naming_strategy";
    @Incubating
    public static final String ID_DB_STRUCTURE_NAMING_STRATEGY = "hibernate.id.db_structure_naming_strategy";
    public static final String ARTIFACT_PROCESSING_ORDER = "hibernate.mapping.precedence";
    public static final String KEYWORD_AUTO_QUOTING_ENABLED = "hibernate.auto_quote_keyword";
    public static final String XML_MAPPING_ENABLED = "hibernate.xml_mapping_enabled";
    public static final String DEFAULT_LIST_SEMANTICS = "hibernate.mapping.default_list_semantics";
    public static final String SESSION_FACTORY_NAME = "hibernate.session_factory_name";
    public static final String SESSION_FACTORY_NAME_IS_JNDI = "hibernate.session_factory_name_is_jndi";
    public static final String SHOW_SQL = "hibernate.show_sql";
    public static final String FORMAT_SQL = "hibernate.format_sql";
    public static final String HIGHLIGHT_SQL = "hibernate.highlight_sql";
    public static final String USE_SQL_COMMENTS = "hibernate.use_sql_comments";
    public static final String MAX_FETCH_DEPTH = "hibernate.max_fetch_depth";
    public static final String DEFAULT_BATCH_FETCH_SIZE = "hibernate.default_batch_fetch_size";
    public static final String USE_SCROLLABLE_RESULTSET = "hibernate.jdbc.use_scrollable_resultset";
    public static final String USE_GET_GENERATED_KEYS = "hibernate.jdbc.use_get_generated_keys";
    public static final String STATEMENT_FETCH_SIZE = "hibernate.jdbc.fetch_size";
    public static final String STATEMENT_BATCH_SIZE = "hibernate.jdbc.batch_size";
    public static final String BATCH_STRATEGY = "hibernate.jdbc.factory_class";
    public static final String BATCH_VERSIONED_DATA = "hibernate.jdbc.batch_versioned_data";
    public static final String JDBC_TIME_ZONE = "hibernate.jdbc.time_zone";
    public static final String AUTO_CLOSE_SESSION = "hibernate.transaction.auto_close_session";
    public static final String FLUSH_BEFORE_COMPLETION = "hibernate.transaction.flush_before_completion";
    public static final String CONNECTION_HANDLING = "hibernate.connection.handling_mode";
    public static final String CURRENT_SESSION_CONTEXT_CLASS = "hibernate.current_session_context_class";
    public static final String USE_IDENTIFIER_ROLLBACK = "hibernate.use_identifier_rollback";
    public static final String USE_REFLECTION_OPTIMIZER = "hibernate.bytecode.use_reflection_optimizer";
    public static final String CALLABLE_NAMED_PARAMS_ENABLED = "hibernate.query.proc.callable_named_params_enabled";
    public static final String SEMANTIC_QUERY_PRODUCER = "hibernate.query.hql.translator";
    public static final String SEMANTIC_QUERY_TRANSLATOR = "hibernate.query.sqm.translator";
    public static final String QUERY_MULTI_TABLE_MUTATION_STRATEGY = "hibernate.query.mutation_strategy";
    public static final String QUERY_MULTI_TABLE_INSERT_STRATEGY = "hibernate.query.insert_strategy";
    public static final String QUERY_STARTUP_CHECKING = "hibernate.query.startup_check";
    public static final String ORDER_UPDATES = "hibernate.order_updates";
    public static final String ORDER_INSERTS = "hibernate.order_inserts";
    @Incubating
    public static final String JPA_CALLBACKS_ENABLED = "hibernate.jpa_callbacks.enabled";
    public static final String DEFAULT_NULL_ORDERING = "hibernate.order_by.default_null_ordering";
    public static final String LOG_JDBC_WARNINGS = "hibernate.jdbc.log.warnings";
    public static final String BEAN_CONTAINER = "hibernate.resource.beans.container";
    public static final String DELAY_CDI_ACCESS = "hibernate.delay_cdi_access";
    public static final String C3P0_CONFIG_PREFIX = "hibernate.c3p0";
    public static final String C3P0_MAX_SIZE = "hibernate.c3p0.max_size";
    public static final String C3P0_MIN_SIZE = "hibernate.c3p0.min_size";
    public static final String C3P0_TIMEOUT = "hibernate.c3p0.timeout";
    public static final String C3P0_MAX_STATEMENTS = "hibernate.c3p0.max_statements";
    public static final String C3P0_ACQUIRE_INCREMENT = "hibernate.c3p0.acquire_increment";
    public static final String C3P0_IDLE_TEST_PERIOD = "hibernate.c3p0.idle_test_period";
    public static final String PROXOOL_CONFIG_PREFIX = "hibernate.proxool";
    public static final String PROXOOL_XML = "hibernate.proxool.xml";
    public static final String PROXOOL_PROPERTIES = "hibernate.proxool.properties";
    public static final String PROXOOL_EXISTING_POOL = "hibernate.proxool.existing_pool";
    public static final String PROXOOL_POOL_ALIAS = "hibernate.proxool.pool_alias";
    public static final String CACHE_REGION_FACTORY = "hibernate.cache.region.factory_class";
    @Deprecated
    public static final String CACHE_KEYS_FACTORY = "hibernate.cache.keys_factory";
    public static final String USE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
    public static final String USE_QUERY_CACHE = "hibernate.cache.use_query_cache";
    public static final String QUERY_CACHE_FACTORY = "hibernate.cache.query_cache_factory";
    public static final String CACHE_REGION_PREFIX = "hibernate.cache.region_prefix";
    public static final String USE_MINIMAL_PUTS = "hibernate.cache.use_minimal_puts";
    public static final String USE_STRUCTURED_CACHE = "hibernate.cache.use_structured_entries";
    public static final String AUTO_EVICT_COLLECTION_CACHE = "hibernate.cache.auto_evict_collection_cache";
    public static final String USE_DIRECT_REFERENCE_CACHE_ENTRIES = "hibernate.cache.use_reference_entries";
    public static final String GLOBALLY_QUOTED_IDENTIFIERS = "hibernate.globally_quoted_identifiers";
    public static final String GLOBALLY_QUOTED_IDENTIFIERS_SKIP_COLUMN_DEFINITIONS = "hibernate.globally_quoted_identifiers_skip_column_definitions";
    public static final String CHECK_NULLABILITY = "hibernate.check_nullability";
    public static final String BYTECODE_PROVIDER = "hibernate.bytecode.provider";
    public static final String JPAQL_STRICT_COMPLIANCE = "hibernate.query.jpaql_strict_compliance";
    public static final String PREFERRED_POOLED_OPTIMIZER = "hibernate.id.optimizer.pooled.preferred";
    public static final String QUERY_PLAN_CACHE_ENABLED = "hibernate.query.plan_cache_enabled";
    public static final String QUERY_PLAN_CACHE_MAX_SIZE = "hibernate.query.plan_cache_max_size";
    @Deprecated(since = "6.0")
    public static final String QUERY_PLAN_CACHE_PARAMETER_METADATA_MAX_SIZE = "hibernate.query.plan_parameter_metadata_max_size";
    public static final String NON_CONTEXTUAL_LOB_CREATION = "hibernate.jdbc.lob.non_contextual_creation";
    public static final String HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    @Deprecated
    public static final String HBM2DDL_DATABASE_ACTION = "javax.persistence.schema-generation.database.action";
    @Deprecated
    public static final String HBM2DDL_SCRIPTS_ACTION = "javax.persistence.schema-generation.scripts.action";
    @Deprecated
    public static final String HBM2DDL_CONNECTION = "javax.persistence.schema-generation-connection";
    public static final String HBM2DDL_CREATE_SOURCE = "javax.persistence.schema-generation.create-source";
    public static final String HBM2DDL_DROP_SOURCE = "javax.persistence.schema-generation.drop-source";
    public static final String HBM2DDL_CREATE_SCRIPT_SOURCE = "javax.persistence.schema-generation.create-script-source";
    public static final String HBM2DDL_DROP_SCRIPT_SOURCE = "javax.persistence.schema-generation.drop-script-source";
    public static final String HBM2DDL_SCRIPTS_CREATE_TARGET = "javax.persistence.schema-generation.scripts.create-target";
    public static final String HBM2DDL_SCRIPTS_CREATE_APPEND = "hibernate.hbm2ddl.schema-generation.script.append";
    public static final String HBM2DDL_SCRIPTS_DROP_TARGET = "javax.persistence.schema-generation.scripts.drop-target";
    public static final String HBM2DDL_IMPORT_FILES = "hibernate.hbm2ddl.import_files";
    @Deprecated
    public static final String HBM2DDL_LOAD_SCRIPT_SOURCE = "javax.persistence.sql-load-script-source";
    public static final String HBM2DDL_IMPORT_FILES_SQL_EXTRACTOR = "hibernate.hbm2ddl.import_files_sql_extractor";
    public static final String HBM2DDL_CREATE_NAMESPACES = "hibernate.hbm2ddl.create_namespaces";
    @Deprecated
    public static final String HBM2DDL_CREATE_SCHEMAS = "javax.persistence.create-database-schemas";
    public static final String JAKARTA_HBM2DDL_DATABASE_ACTION = "jakarta.persistence.schema-generation.database.action";
    public static final String JAKARTA_HBM2DDL_SCRIPTS_ACTION = "jakarta.persistence.schema-generation.scripts.action";
    public static final String JAKARTA_HBM2DDL_CONNECTION = "jakarta.persistence.schema-generation-connection";
    public static final String JAKARTA_HBM2DDL_DB_NAME = "jakarta.persistence.database-product-name";
    public static final String JAKARTA_HBM2DDL_DB_VERSION = "jakarta.persistence.database-product-version";
    public static final String JAKARTA_HBM2DDL_DB_MAJOR_VERSION = "jakarta.persistence.database-major-version";
    public static final String JAKARTA_HBM2DDL_DB_MINOR_VERSION = "jakarta.persistence.database-minor-version";
    public static final String JAKARTA_HBM2DDL_CREATE_SOURCE = "jakarta.persistence.schema-generation.create-source";
    public static final String JAKARTA_HBM2DDL_DROP_SOURCE = "jakarta.persistence.schema-generation.drop-source";
    public static final String JAKARTA_HBM2DDL_CREATE_SCRIPT_SOURCE = "jakarta.persistence.schema-generation.create-script-source";
    public static final String JAKARTA_HBM2DDL_DROP_SCRIPT_SOURCE = "jakarta.persistence.schema-generation.drop-script-source";
    public static final String JAKARTA_HBM2DDL_SCRIPTS_CREATE_TARGET = "jakarta.persistence.schema-generation.scripts.create-target";
    public static final String JAKARTA_HBM2DDL_SCRIPTS_DROP_TARGET = "jakarta.persistence.schema-generation.scripts.drop-target";
    public static final String JAKARTA_HBM2DDL_LOAD_SCRIPT_SOURCE = "jakarta.persistence.sql-load-script-source";
    public static final String JAKARTA_HBM2DDL_CREATE_SCHEMAS = "jakarta.persistence.create-database-schemas";
    public static final String HBM2DDL_FILTER_PROVIDER = "hibernate.hbm2ddl.schema_filter_provider";
    public static final String HBM2DDL_JDBC_METADATA_EXTRACTOR_STRATEGY = "hibernate.hbm2ddl.jdbc_metadata_extraction_strategy";
    public static final String HBM2DDL_DELIMITER = "hibernate.hbm2ddl.delimiter";
    public static final String HBM2DDL_CHARSET_NAME = "hibernate.hbm2ddl.charset_name";
    public static final String HBM2DDL_HALT_ON_ERROR = "hibernate.hbm2ddl.halt_on_error";
    public static final String HBM2DDL_DEFAULT_CONSTRAINT_MODE = "hibernate.hbm2ddl.default_constraint_mode";
    public static final String CUSTOM_ENTITY_DIRTINESS_STRATEGY = "hibernate.entity_dirtiness_strategy";
    public static final String USE_ENTITY_WHERE_CLAUSE_FOR_COLLECTIONS = "hibernate.use_entity_where_clause_for_collections";
    public static final String MULTI_TENANT_CONNECTION_PROVIDER = "hibernate.multi_tenant_connection_provider";
    public static final String MULTI_TENANT_IDENTIFIER_RESOLVER = "hibernate.tenant_identifier_resolver";
    public static final String INTERCEPTOR = "hibernate.session_factory.interceptor";
    public static final String SESSION_SCOPED_INTERCEPTOR = "hibernate.session_factory.session_scoped_interceptor";
    public static final String STATEMENT_INSPECTOR = "hibernate.session_factory.statement_inspector";
    public static final String ENABLE_LAZY_LOAD_NO_TRANS = "hibernate.enable_lazy_load_no_trans";
    @Deprecated(since = "6.0")
    public static final String BATCH_FETCH_STYLE = "hibernate.batch_fetch_style";
    public static final String DELAY_ENTITY_LOADER_CREATIONS = "hibernate.loader.delay_entity_loader_creations";
    public static final String JTA_TRACK_BY_THREAD = "hibernate.jta.track_by_thread";
    public static final String ENABLE_SYNONYMS = "hibernate.synonyms";
    public static final String EXTRA_PHYSICAL_TABLE_TYPES = "hibernate.hbm2ddl.extra_physical_table_types";
    public static final String UNIQUE_CONSTRAINT_SCHEMA_UPDATE_STRATEGY = "hibernate.schema_update.unique_constraint_strategy";
    public static final String GENERATE_STATISTICS = "hibernate.generate_statistics";
    public static final String LOG_SESSION_METRICS = "hibernate.session.events.log";
    public static final String LOG_SLOW_QUERY = "hibernate.session.events.log.LOG_QUERIES_SLOWER_THAN_MS";
    public static final String AUTO_SESSION_EVENTS_LISTENER = "hibernate.session.events.auto";
    public static final String CREATE_EMPTY_COMPOSITES_ENABLED = "hibernate.create_empty_composites.enabled";
    public static final String ALLOW_JTA_TRANSACTION_ACCESS = "hibernate.jta.allowTransactionAccess";
    public static final String ALLOW_UPDATE_OUTSIDE_TRANSACTION = "hibernate.allow_update_outside_transaction";
    public static final String ALLOW_REFRESH_DETACHED_ENTITY = "hibernate.allow_refresh_detached_entity";
    public static final String MERGE_ENTITY_COPY_OBSERVER = "hibernate.event.merge.entity_copy_observer";
    public static final String CRITERIA_VALUE_HANDLING_MODE = "hibernate.criteria.value_handling_mode";
    public static final String CRITERIA_COPY_TREE = "hibernate.criteria.copy_tree";
    public static final String JPA_COMPLIANCE = "hibernate.jpa.compliance";
    public static final String JPA_TRANSACTION_COMPLIANCE = "hibernate.jpa.compliance.transaction";
    public static final String JPA_QUERY_COMPLIANCE = "hibernate.jpa.compliance.query";
    @Deprecated(since = "6.0")
    public static final String JPA_LIST_COMPLIANCE = "hibernate.jpa.compliance.list";
    public static final String JPA_ORDER_BY_MAPPING_COMPLIANCE = "hibernate.jpa.compliance.orderby";
    public static final String JPA_CLOSED_COMPLIANCE = "hibernate.jpa.compliance.closed";
    public static final String JPA_PROXY_COMPLIANCE = "hibernate.jpa.compliance.proxy";
    public static final String JPA_CACHING_COMPLIANCE = "hibernate.jpa.compliance.caching";
    public static final String JPA_ID_GENERATOR_GLOBAL_SCOPE_COMPLIANCE = "hibernate.jpa.compliance.global_id_generators";
    public static final String JPA_LOAD_BY_ID_COMPLIANCE = "hibernate.jpa.compliance.load_by_id";
    public static final String TABLE_GENERATOR_STORE_LAST_USED = "hibernate.id.generator.stored_last_used";
    public static final String FAIL_ON_PAGINATION_OVER_COLLECTION_FETCH = "hibernate.query.fail_on_pagination_over_collection_fetch";
    public static final String IMMUTABLE_ENTITY_UPDATE_QUERY_HANDLING_MODE = "hibernate.query.immutable_entity_update_query_handling_mode";
    public static final String IN_CLAUSE_PARAMETER_PADDING = "hibernate.query.in_clause_parameter_padding";
    public static final String QUERY_STATISTICS_MAX_SIZE = "hibernate.statistics.query_max_size";
    public static final String SEQUENCE_INCREMENT_SIZE_MISMATCH_STRATEGY = "hibernate.id.sequence.increment_size_mismatch_strategy";
    @Incubating
    public static final String PREFERRED_BOOLEAN_JDBC_TYPE = "hibernate.type.preferred_boolean_jdbc_type";
    @Incubating
    public static final String PREFERRED_UUID_JDBC_TYPE = "hibernate.type.preferred_uuid_jdbc_type";
    @Incubating
    public static final String PREFERRED_DURATION_JDBC_TYPE = "hibernate.type.preferred_duration_jdbc_type";
    @Incubating
    public static final String PREFERRED_INSTANT_JDBC_TYPE = "hibernate.type.preferred_instant_jdbc_type";
    public static final String JSON_FORMAT_MAPPER = "hibernate.type.json_format_mapper";
    public static final String XML_FORMAT_MAPPER = "hibernate.type.xml_format_mapper";
    public static final String TIMEZONE_DEFAULT_STORAGE = "hibernate.timezone.default_storage";
    @Deprecated
    public static final String JPA_PERSISTENCE_PROVIDER = "javax.persistence.provider";
    @Deprecated
    public static final String JPA_TRANSACTION_TYPE = "javax.persistence.transactionType";
    @Deprecated
    public static final String JPA_JTA_DATASOURCE = "javax.persistence.jtaDataSource";
    @Deprecated
    public static final String JPA_NON_JTA_DATASOURCE = "javax.persistence.nonJtaDataSource";
    @Deprecated
    public static final String JPA_JDBC_DRIVER = "javax.persistence.jdbc.driver";
    @Deprecated
    public static final String JPA_JDBC_URL = "javax.persistence.jdbc.url";
    @Deprecated
    public static final String JPA_JDBC_USER = "javax.persistence.jdbc.user";
    @Deprecated
    public static final String JPA_JDBC_PASSWORD = "javax.persistence.jdbc.password";
    @Deprecated
    public static final String JPA_SHARED_CACHE_MODE = "javax.persistence.sharedCache.mode";
    @Deprecated
    public static final String JPA_SHARED_CACHE_RETRIEVE_MODE = "javax.persistence.cache.retrieveMode";
    @Deprecated
    public static final String JPA_SHARED_CACHE_STORE_MODE = "javax.persistence.cache.storeMode";
    @Deprecated
    public static final String JPA_VALIDATION_MODE = "javax.persistence.validation.mode";
    @Deprecated
    public static final String JPA_VALIDATION_FACTORY = "javax.persistence.validation.factory";
    @Deprecated
    public static final String JPA_PERSIST_VALIDATION_GROUP = "javax.persistence.validation.group.pre-persist";
    @Deprecated
    public static final String JPA_UPDATE_VALIDATION_GROUP = "javax.persistence.validation.group.pre-update";
    @Deprecated
    public static final String JPA_REMOVE_VALIDATION_GROUP = "javax.persistence.validation.group.pre-remove";
    @Deprecated
    public static final String JPA_LOCK_SCOPE = "javax.persistence.lock.scope";
    @Deprecated
    public static final String JPA_LOCK_TIMEOUT = "javax.persistence.lock.timeout";
    @Deprecated
    public static final String CDI_BEAN_MANAGER = "javax.persistence.bean.manager";
    public static final String FLUSH_MODE = "org.hibernate.flushMode";
    public static final String CFG_XML_FILE = "hibernate.cfg_xml_file";
    public static final String ORM_XML_FILES = "hibernate.orm_xml_files";
    public static final String HBM_XML_FILES = "hibernate.hbm_xml_files";
    public static final String LOADED_CLASSES = "hibernate.loaded_classes";
    public static final String EVENT_LISTENER_PREFIX = "hibernate.event.listener";
    public static final String CLASS_CACHE_PREFIX = "hibernate.classcache";
    public static final String COLLECTION_CACHE_PREFIX = "hibernate.collectioncache";
    public static final String ENHANCER_ENABLE_DIRTY_TRACKING = "hibernate.enhancer.enableDirtyTracking";
    public static final String ENHANCER_ENABLE_LAZY_INITIALIZATION = "hibernate.enhancer.enableLazyInitialization";
    public static final String ENHANCER_ENABLE_ASSOCIATION_MANAGEMENT = "hibernate.enhancer.enableAssociationManagement";
    public static final String PERSISTENCE_UNIT_NAME = "hibernate.persistenceUnitName";
    public static final String SESSION_FACTORY_OBSERVER = "hibernate.session_factory_observer";
    @Deprecated(since = "6.0")
    public static final String IDENTIFIER_GENERATOR_STRATEGY_PROVIDER = "hibernate.identifier_generator_strategy_provider";
    public static final String DISCARD_PC_ON_CLOSE = "hibernate.discard_pc_on_close";
    public static final String VALIDATE_XML = "hibernate.validate_xml";
    public static final String TRANSFORM_HBM_XML = "hibernate.transform_hbm_xml.enabled";
    public static final String TRANSFORM_HBM_XML_FEATURE_HANDLING = "hibernate.transform_hbm_xml.unsupported_feature_handling";
}