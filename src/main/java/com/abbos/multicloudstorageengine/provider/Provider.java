package com.abbos.multicloudstorageengine.provider;

import com.abbos.multicloudstorageengine.context.ExecutionContext;
import com.abbos.multicloudstorageengine.context.Pair;
import com.abbos.multicloudstorageengine.enums.MetadataKey;
import com.abbos.multicloudstorageengine.model.Capabilities;

import java.util.Optional;

/**
 * Defines the foundational contract for all storage providers in the multi-cloud storage engine.
 * Implementing classes must provide a name and a set of capabilities indicating supported operational
 * features such as synchronous, asynchronous, or reactive operations.
 *
 * <p>This interface serves as the base for specialized storage provider interfaces, ensuring a consistent
 * contract for querying provider identity and capabilities across implementations.
 *
 * @author Aliabbos Ashurov
 * @since 02/March/2025 08:50
 */
public interface Provider {

    /**
     * Retrieves the name of the storage provider.
     *
     * @return a string representing the unique name of the provider
     */
    String getProviderName();

    /**
     * Retrieves the capabilities of the storage provider, indicating support for asynchronous
     * and reactive operations.
     *
     * @return a {@link Capabilities} instance detailing the provider's supported features
     */
    Capabilities getCapabilities();

    /**
     * Retrieves a metadata value from the execution context based on the specified metadata key.
     * If the key exists and the value matches the expected type, it returns an {@link Optional}
     * containing the value; otherwise, it returns an empty {@link Optional}.
     *
     * @param context      the execution context containing metadata
     * @param targetKey    the {@link MetadataKey} to search for
     * @param expectedType the expected type of the metadata value
     * @param <T>          the type of the metadata value
     * @return an {@link Optional} containing the metadata value if found and type matches,
     * otherwise an empty {@link Optional}
     */
    default <T> Optional<T> getMetadataValue(ExecutionContext context, MetadataKey targetKey, Class<T> expectedType) {
        return context.getMetadata().stream()
                .filter(pair -> pair.getLeft().equals(targetKey) && expectedType.isInstance(pair.getRight()))
                .map(pair -> expectedType.cast(pair.getRight()))
                .findFirst();
    }
}
