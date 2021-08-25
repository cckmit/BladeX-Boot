package org.springblade.modules.resource.utils;

import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.FileUtil;
import org.springblade.core.tool.utils.StringUtil;

public class minioUtil {

		public String fileName(String originalFilename) {
			return "upload/" + DateUtil.today() + "/" + StringUtil.randomUUID() + "." + FileUtil.getFileExtension(originalFilename);
		}

}
