package aurionpro.erp.ipms.assetmgmt.citywiseinstallation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AssetCityWiseInstallationViewRepository extends JpaRepository<AssetCityWiseInstallationView,Long>{

	Optional<AssetCityWiseInstallationMaster> findByCity(String city);

}