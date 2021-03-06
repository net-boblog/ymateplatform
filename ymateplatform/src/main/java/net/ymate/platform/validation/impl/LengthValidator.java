/*
 * Copyright 2007-2107 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ymate.platform.validation.impl;

import java.util.Map;

import net.ymate.platform.base.YMP;
import net.ymate.platform.commons.i18n.I18N;
import net.ymate.platform.validation.AbstractValidator;
import net.ymate.platform.validation.IValidateContext;
import net.ymate.platform.validation.ValidationException;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * LengthValidator
 * </p>
 * <p>
 * 字符串长度验证；只处理字符串类型数据
 * </p>
 * 
 * @author 刘镇(suninformation@163.com)
 * @version 0.0.0
 *          <table style="border:1px solid gray;">
 *          <tr>
 *          <th width="100px">版本号</th><th width="100px">动作</th><th
 *          width="100px">修改人</th><th width="100px">修改时间</th>
 *          </tr>
 *          <!-- 以 Table 方式书写修改历史 -->
 *          <tr>
 *          <td>0.0.0</td>
 *          <td>创建类</td>
 *          <td>刘镇</td>
 *          <td>2013-4-17下午5:17:22</td>
 *          </tr>
 *          </table>
 */
public class LengthValidator extends AbstractValidator {

	public static final String NAME = "length";

	/* (non-Javadoc)
	 * @see net.ymate.platform.validation.IValidator#getName()
	 */
	public String getName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see net.ymate.platform.validation.AbstractValidator#onValidate(net.ymate.platform.validation.IValidateContext)
	 */
	@Override
	protected String onValidate(IValidateContext context) {
		if (doParamsLengthCheck(context, 1)) {
			if (isString(context.getFieldValue().getClass())) {
				String _value = (String) context.getFieldValue();
				if (StringUtils.isNotBlank(_value)) {
					Map<String, String> _params = getParamMaps(context);
					boolean _hasMin = _params.containsKey("min");
					boolean _hasMax = _params.containsKey("max");
					int _min = 0;
					int _max = 0;
					if (_hasMin) {
						_min = Integer.parseInt(_params.get("min"));
						if (_min <=0) {
							_hasMin = false;
						}
					}
					if (_hasMax) {
						_max = Integer.parseInt(_params.get("max"));
						if (_max <= 0 || (_hasMin && _max < _min)) {
							_hasMax = false;
						}
					}
					if (!_hasMin && !_hasMax) {
						throw new IllegalArgumentException(I18N.formatMessage(YMP.__LSTRING_FILE, null, null, "ymp.validation.validator_parameter_invalid"));
					}
					if (_hasMax) {
						if (_value.length() <= _max) {
							if (_hasMin) {
								if (_value.length() >= _min) {
									return VALIDATE_SUCCESS;
								}
							} else {
								return VALIDATE_SUCCESS;
							}
						}
					} else if (_hasMin) {
						if (_value.length() >= _min) {
							return VALIDATE_SUCCESS;
						}
					}
					return doMessageResult(context, getResultMessageI18nStr(context, _hasMin, _hasMax, _min, _max), _min + "", _max + "");
				}
			} else {
				throw new ValidationException(I18N.formatMessage(YMP.__LSTRING_FILE, null, null, "ymp.validation.unsupport_non_string_cond_op"));
			}
		} else {
			throw new IllegalArgumentException(I18N.formatMessage(YMP.__LSTRING_FILE, null, null, "ymp.validation.validator_parameter_invalid"));
		}
		return onValidateNull(context);
	}

	/* (non-Javadoc)
	 * @see net.ymate.platform.validation.AbstractValidator#onValidateArray(net.ymate.platform.validation.IValidateContext, java.lang.Class)
	 */
	@Override
	protected String onValidateArray(IValidateContext context, Class<?> arrayClassType) {
		throw new ValidationException(I18N.formatMessage(YMP.__LSTRING_FILE, null, null, "ymp.validation.unsupport_non_string_cond_op"));
	}

	/**
	 * @param context 验证器上下文对象
	 * @return 获取回应消息字符串，非i18n环境请设置为null
	 */
	protected String getResultMessageI18nStr(IValidateContext context, boolean hasMin, boolean hasMax, int min, int max) {
		String _returnValue = "ymp.validation.length";
		if (hasMax && hasMin) {
			return _returnValue;
		} else if (hasMin) {
			return _returnValue + "_min";
		}
		return _returnValue + "_max";
	}

}
