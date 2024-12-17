package aurionpro.erp.ipms.openbravo.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenBravoProductRepository extends JpaRepository<OpenBravoProductMaster, String> {

	//Optional<OpenBravoProductMaster> findByProductname(String productname);
}
