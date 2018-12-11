package ma.co.orimex.stock.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of AchatFournisseurSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class AchatFournisseurSearchRepositoryMockConfiguration {

    @MockBean
    private AchatFournisseurSearchRepository mockAchatFournisseurSearchRepository;

}
