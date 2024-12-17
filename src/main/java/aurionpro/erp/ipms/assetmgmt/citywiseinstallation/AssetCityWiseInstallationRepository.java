package aurionpro.erp.ipms.assetmgmt.citywiseinstallation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AssetCityWiseInstallationRepository extends JpaRepository<AssetCityWiseInstallationMaster,Long>{

	Optional<AssetCityWiseInstallationMaster> findByCity(String city);

}