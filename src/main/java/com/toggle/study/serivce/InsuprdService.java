package com.toggle.study.serivce;

import java.util.List;
import java.util.Map;

import com.toggle.study.mapper.InsuprdMapper;
import com.toggle.study.model.InsuprdDTO;
import com.toggle.study.model.InsuprdDestnDTO;
import com.toggle.study.model.InsuprdFturDTO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuprdService {

	@Autowired
	protected SqlSession sqlsession;

    @Autowired
    private InsuprdMapper insuprdMapper;

	public List<InsuprdDTO> selectInsuprd() throws Exception {
		return insuprdMapper.selectInsuprd();
	}

	//상품설명조회
	public InsuprdDestnDTO saleInsuprd(Map<String, Object> param) throws Exception {

		InsuprdDestnDTO destn=insuprdMapper.saleInsuprd(param);
		List<InsuprdFturDTO> test= insuprdMapper.insuprdftur(param);

		System.out.println("test ::" +test);

		//List<InsuprdFturDTO> ftur=sqlsession.selectList("com.toggle.study.mapper.InsuprdMapper.insuprdftur",param);

		destn.setFur(test);

		return destn;
	}
}
