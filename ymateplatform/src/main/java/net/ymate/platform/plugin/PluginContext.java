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
package net.ymate.platform.plugin;

/**
 * <p>
 * PluginContext
 * </p>
 * <p>
 * 插件环境上下文对象；
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
 *          <td>2011-10-18上午10:39:03</td>
 *          </tr>
 *          </table>
 */
public class PluginContext {

    private IPluginFactory __pluginFactory;
	private PluginMeta __pluginMeta;

    /**
     * 构造器
     *
     * @param pluginFactory
     * @param pluginMeta
     */
	public PluginContext(IPluginFactory pluginFactory, PluginMeta pluginMeta) {
        this.__pluginFactory = pluginFactory;
		this.__pluginMeta = pluginMeta;
	}

	/**
	 * @return 插件相关信息描述类
	 */
	public PluginMeta getPluginMeta() {
		return this.__pluginMeta;
	}

    public IPluginFactory getPluginFactory() {
        return this.__pluginFactory;
    }

}
