/**
 *
 */
package com.solverpeng.modules.product.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trekiz.admin.common.config.Global;
import com.trekiz.admin.common.persistence.Page;
import com.trekiz.admin.common.web.BaseController;
import com.trekiz.admin.modules.sys.entity.User;
import com.trekiz.admin.modules.sys.utils.UserUtils;
import com.solverpeng.modules.product.entity.Product;
import com.solverpeng.modules.product.service.ProductService;

/**
 * 产品Controller
 * @author solverpeng
 * @version 2017-02-24
 */
@Controller
@RequestMapping(value = "${adminPath}/product/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;
	
	@ModelAttribute
	public Product get(@RequestParam(required=false) Long id) {
		if (id != null){
			return productService.get(id);
		}else{
			return new Product();
		}
	}
	
	@RequiresPermissions("product:product:view")
	@RequestMapping(value = {"list", ""})
	public String list(Product product, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			product.setCreateBy(user);
		}
        Page<Product> page = productService.find(new Page<Product>(request, response), product); 
        model.addAttribute("page", page);
		return "modules/product/productList";
	}

	@RequiresPermissions("product:product:view")
	@RequestMapping(value = "form")
	public String form(Product product, Model model) {
		model.addAttribute("product", product);
		return "modules/product/productForm";
	}

	@RequiresPermissions("product:product:edit")
	@RequestMapping(value = "save")
	public String save(Product product, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, product)){
			return form(product, model);
		}
		productService.save(product);
		addMessage(redirectAttributes, "保存产品'" + product.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/modules/product/product/?repage";
	}
	
	@RequiresPermissions("product:product:edit")
	@RequestMapping(value = "delete")
	public String delete(Long id, RedirectAttributes redirectAttributes) {
		productService.delete(id);
		addMessage(redirectAttributes, "删除产品成功");
		return "redirect:"+Global.getAdminPath()+"/modules/product/product/?repage";
	}

}
