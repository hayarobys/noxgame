package kr.pe.hayarobys.nox.common.proj;

import org.osgeo.proj4j.BasicCoordinateTransform;
import org.osgeo.proj4j.CRSFactory;
import org.osgeo.proj4j.CoordinateReferenceSystem;
import org.osgeo.proj4j.ProjCoordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProjTestService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// GRS80 UTM-K 좌표를 WGS84로 변환한 경도, 위도 값 반환
	// 요청: https://localhost:8443/nox/proj/test?longitude=956273.402095&latitude=1953436.321383
	// 응답: [127.00477726752297, 37.57927243684657]
	// 서울특별시 종로구 동숭동 이화장길 94-0 동숭교회
	public String test(double longitude, double latitude) {
		CRSFactory factory = new CRSFactory();
		CoordinateReferenceSystem srcCRS = factory.createFromName("EPSG:5179"); // UTM-K(GRS80)
		CoordinateReferenceSystem dstCRS = factory.createFromName("EPSG:4326"); // WGS84
		
		BasicCoordinateTransform transform = new BasicCoordinateTransform(srcCRS, dstCRS);
		
		// Note these are x, y so lng(경도), lat(위도)
		ProjCoordinate srcCoord = new ProjCoordinate(longitude, latitude);
		ProjCoordinate dstCoord = new ProjCoordinate();
		
		// Writes result into dstCoord
		transform.transform(srcCoord, dstCoord);
		logger.debug("변환할 좌표: {} -> 변환된 좌표: {}", srcCoord, dstCoord);
		return dstCoord.toShortString();
	}
}
