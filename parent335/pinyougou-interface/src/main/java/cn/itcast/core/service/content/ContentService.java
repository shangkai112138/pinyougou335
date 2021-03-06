package cn.itcast.core.service.content;

import java.util.List;
import java.util.Map;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.ad.Content;
import cn.itcast.core.pojo.item.ItemCat;

public interface ContentService {

	public List<Content> findAll();

	public PageResult findPage(Content content, Integer pageNum, Integer pageSize);

	public void add(Content content);

	public void edit(Content content);

	public Content findOne(Long id);

	public void delAll(Long[] ids);

	/**
	 * 首页大广告的轮播图
	 * @param categoryId
	 * @return
	 */
	public List<Content> findByCategoryId(Long categoryId);

	/**
	 * 首页所有广告查询
	 * @return
	 */
	Map<Object,Object> findContent();

	/**
	 * 加载商品分类
	 * @param parentId
	 * @return
	 */
	List<ItemCat> findByParentId(Long parentId);
}
