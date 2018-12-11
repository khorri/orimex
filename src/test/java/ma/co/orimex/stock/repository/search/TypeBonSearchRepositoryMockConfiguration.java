package ma.co.orimex.stock.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of TypeBonSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class TypeBonSearchRepositoryMockConfiguration {

    @MockBean
    private TypeBonSearchRepository mockTypeBonSearchRepository;

}
