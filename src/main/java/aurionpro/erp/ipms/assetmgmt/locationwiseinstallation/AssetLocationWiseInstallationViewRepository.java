package aurionpro.erp.ipms.assetmgmt.locationwiseinstallation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AssetLocationWiseInstallationViewRepository extends JpaRepository<AssetLocationWiseInstallationView,Long>{

	Optional<AssetLocationWiseInstallationMaster> findByLocation(String location);

}