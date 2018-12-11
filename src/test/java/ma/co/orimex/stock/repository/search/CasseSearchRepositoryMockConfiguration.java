package ma.co.orimex.stock.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of CasseSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class CasseSearchRepositoryMockConfiguration {

    @MockBean
    private CasseSearchRepository mockCasseSearchRepository;

}
