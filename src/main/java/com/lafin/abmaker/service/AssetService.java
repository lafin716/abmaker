package com.lafin.abmaker.service;

import java.util.List;

import com.lafin.abmaker.dto.AssetDto;
import com.lafin.abmaker.dto.AssetLogDto;
import com.lafin.abmaker.dto.ComponentDto;
import com.lafin.abmaker.form.BoardForm;

public interface AssetService {
	
	public List<AssetDto> getAssetListAll(Integer user_seq);
	public BoardForm getAssetList(BoardForm param);
	public AssetDto getAsset(AssetDto assetsDto);
	public AssetDto getAssetById(Integer asset_seq);
	public boolean addAsset(AssetDto assetsDto);
	public boolean modifyAsset(AssetDto assetsDto);
	public boolean deleteAsset(AssetDto assetsDto);
	public boolean calculate(AssetDto asset, ComponentDto comp, AssetLogDto param);
}
