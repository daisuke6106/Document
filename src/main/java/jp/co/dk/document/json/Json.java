package jp.co.dk.document.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import jp.co.dk.document.File;
import jp.co.dk.document.exception.DocumentException;
import static jp.co.dk.document.message.DocumentMessage.*;

/**
 * Jsonは、ネットワーク上に存在するJSONを表すオブジェクトです
 * 
 * @author D.Kanno
 */
public class Json extends File {
	
	/** JSONデータオブジェクト */
	protected Object data;
	
	/**
	 * <p>コンストラクタ</p>
	 * 
	 * @param inputStream 読み込みストリーム
	 * @throws DocumentException 読み込みストリームからの読み込みに失敗した場合
	 */
	public Json(InputStream inputStream) throws DocumentException {
		super(inputStream);
		try {
			this.data = JSON.decode(this.fileData.getStream());
		} catch (JSONException | IOException e) {
			throw new DocumentException(ERROR_DECORD_TO_JSON, e);
		}
	}
	
	/**
	 * JSONデータをリストとして返却します。
	 * @return JSONデータのリスト
	 */
	public List getDataByList() {
		return new ArrayList((List)this.data);
	}
	
	/**
	 * JSONデータをマップとして返却します。
	 * @return JSONデータのマップ
	 */
	public Map getDataByMap() {
		return new LinkedHashMap((Map)this.data);
	}
}
