package com.lafin.abmaker.mapper;

import java.util.List;

import com.lafin.abmaker.dto.AssetDto;
import com.lafin.abmaker.dto.AssetLogDto;
import com.lafin.abmaker.form.BoardForm;

public interface AssetListMapper {
	public Integer getTotal(Integer user_seq);
	public List<AssetDto> getAssetListAll(Integer user_seq);
	public List<AssetDto> getAssetList(BoardForm param);
	public AssetDto getAssetById(Integer asset_seq);
	public AssetDto getAsset(AssetDto param);
	public boolean addAsset(AssetDto param);
	public boolean deleteAsset(AssetDto param);
	public boolean modifyAsset(AssetDto param);
	
	public boolean calculate(AssetDto param);
	public boolean addLog(AssetLogDto param);
}
