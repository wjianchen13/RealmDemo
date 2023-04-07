package com.example.realmdemo.zip;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/***************************************************
 *
 * zip下载表
 *
 ***************************************************/
public class ZipBean extends RealmObject {

	@PrimaryKey
	private long zid; // 1
    private long zipDownloadSize;      // 已经下载大小
    private long zipAllSize;    // zip文件总大小
    private String zipUrl = "";// 文件下载路径
    private String zipName = "";    // zip文件标识
    private int zipVersion;   // 版本号

	public ZipBean() {

	}

	public ZipBean(ZipBean bean) {
		if(bean != null) {
			this.zid = bean.getZid();
			this.zipDownloadSize = bean.getZipDownloadSize();
			this.zipAllSize = bean.getZipAllSize();
			this.zipUrl = bean.getZipUrl();
			this.zipName = bean.getZipName();
			this.zipVersion = bean.getZipVersion();
		}

	}

	public ZipBean(Long zid, long zipDownloadSize, long zipAllSize, String zipUrl, String zipName, int zipVersion) {
		this.zid = zid;
		this.zipDownloadSize = zipDownloadSize;
		this.zipAllSize = zipAllSize;
		this.zipUrl = zipUrl;
		this.zipName = zipName;
		this.zipVersion = zipVersion;
	}

	public Long getZid() {
		return zid;
	}

	public void setZid(Long zid) {
		this.zid = zid;
	}

	public long getZipDownloadSize() {
		return zipDownloadSize;
	}

	public void setZipDownloadSize(long zipDownloadSize) {
		this.zipDownloadSize = zipDownloadSize;
	}

	public long getZipAllSize() {
		return zipAllSize;
	}

	public void setZipAllSize(long zipAllSize) {
		this.zipAllSize = zipAllSize;
	}

	public String getZipUrl() {
		return zipUrl;
	}

	public void setZipUrl(String zipUrl) {
		this.zipUrl = zipUrl;
	}

	public String getZipName() {
		return zipName;
	}

	public void setZipName(String zipName) {
		this.zipName = zipName;
	}

	public int getZipVersion() {
		return zipVersion;
	}

	public void setZipVersion(int zipVersion) {
		this.zipVersion = zipVersion;
	}

	@Override
	public String toString() {
		return "ZipBean{" +
				"zid=" + zid +
				", zipDownloadSize=" + zipDownloadSize +
				", zipAllSize=" + zipAllSize +
				", zipUrl='" + zipUrl + '\'' +
				", zipName='" + zipName + '\'' +
				", zipVersion=" + zipVersion +
				'}';
	}
}
