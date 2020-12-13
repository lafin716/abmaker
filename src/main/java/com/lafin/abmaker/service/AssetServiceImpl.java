package com.lafin.abmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lafin.abmaker.dto.AssetDto;
import com.lafin.abmaker.dto.AssetLogDto;
import com.lafin.abmaker.dto.ComponentDto;
import com.lafin.abmaker.form.BoardForm;
import com.lafin.abmaker.mapper.AssetListMapper;
import com.lafin.abmaker.util.PagingUtil;

@Service("assetService")
public class AssetServiceImpl implements AssetService {
	
	@Autowired
	private AssetListMapper assetListMapper;
	
	@Override
	public List<AssetDto> getAssetListAll(Integer user_seq) {
		List<AssetDto> list = assetListMapper.getAssetListAll(user_seq);
		return list;
	}

	@Override
	public BoardForm getAssetList(BoardForm form) {
		// 페이징 설정
		Integer totalCount = assetListMapper.getTotal(form.getUser_seq());
		PagingUtil paging = new PagingUtil();
		paging.createPaging(totalCount, form.getPage());		
		form.setPaging(paging);
		
		List<AssetDto> resultList = assetListMapper.getAssetList(form);
		BoardForm result = new BoardForm();
		
		result.setResult(resultList);
		result.setPaging(paging);
		
		return result;
	}
	
	@Override
	public AssetDto getAssetById(Integer asset_seq) {
		return assetListMapper.getAssetById(asset_seq);
	}

	@Override
	public AssetDto getAsset(AssetDto param) {		
		return assetListMapper.getAsset(param);
	}

	@Override
	public boolean addAsset(AssetDto param) {
		return assetListMapper.addAsset(param);
	}

	@Override
	public boolean modifyAsset(AssetDto param) {
		return assetListMapper.modifyAsset(param);
	}

	@Override
	public boolean deleteAsset(AssetDto param) {
		return assetListMapper.deleteAsset(param);
	}

	@Override
	public boolean calculate(AssetDto asset, ComponentDto comp, AssetLogDto param) {
		
		Integer originAmount = asset.getAmount();
		Integer targetAmount = param.getAmount();
		Integer resultAmount = 0;
		String assetType = asset.getType();
		
		switch(assetType) {
			case "plus":
				resultAmount = originAmount + targetAmount;
				break;
			case "minus":
				resultAmount = originAmount - targetAmount;
				break;
		}
		
		// 계산된 값으로 설정
		asset.setAmount(resultAmount);
		assetListMapper.calculate(asset);
		
		// 로그 추가
		param.setAsset_name(asset.getAsset_name());
		param.setComp_name(comp.getComp_name());		
		assetListMapper.addLog(param);
		
		// TODO Auto-generated method stub
		return false;
	}

}
