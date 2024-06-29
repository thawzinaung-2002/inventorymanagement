package spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.dto.CategoryDTO;
import spring.dto.ProductDTO;
import spring.dto.ProductLotDTO;
import spring.model.ProductLotBean;
import spring.service.CategoryService;
import spring.service.LotService;
import spring.service.ProductLotService;
import spring.service.ProductService;

@Controller
@RequestMapping(value="product")
public class ProductController {
	
	@Autowired
	ProductLotService productLotService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	LotService lotService;
	
	@Autowired
	ProductService productService;
	
public static int num = 1;
	
	public String generate()
	{
		String str = lotService.getLog();
		if(str == null)
		{
			return "LOT" + num;
		}
		else
		{
			Integer id = Integer.valueOf(str.substring(3).trim());
			return "LOT" + (++id); 
		}
	}

	@GetMapping(value="/lists")
	public String showProducts(ModelMap model)
	{
		List<ProductDTO> dbRs = productService.getAll();
		model.addAttribute("products", dbRs);
		return "product-lists";
	}
	
	@GetMapping(value="/more/{id}")
	public String showMore(@PathVariable("id")String id, ModelMap model)
	{
		List<ProductLotDTO> dbRs = productLotService.getOneById(id);
		model.addAttribute("products", dbRs);
		return "product-details";
	}
	
	@GetMapping(value="/add")
	public String showAdd(ModelMap model)
	{
		ProductBean bean = new ProductBean();
		bean.setCategory(getCategories().get(0));
		bean.setUom(getUoms().get(0));
		model.addAttribute("product", bean);
		return "product-add";
	}
	
	@PostMapping(value="/add")
	public String doAdd(@ModelAttribute("productLot")ProductLotBean productLot)
	{
		List<ProductLotDTO> products = productLotService.getAll();
		productLot.setLot(generate());
		for(ProductLotDTO dto: products)
		{
			if(dto.getExpired().equals(productLot.getExpired()) && dto.getName().equals(productLot.getName()))
			{
				ProductLotDTO dbRs = productLotService.getByLot(dto.getLot());
				productLot.setLot(dbRs.getLot());
				productLot.setQuantity(dbRs.getQuantity() + productLot.getQuantity());
				ProductLotDTO updateDto=mapper.map(productLot, ProductLotDTO.class);
				productLotService.updateOne(updateDto);
				return "redirect:./lists";
			}
		}
		
		ProductLotDTO dto=mapper.map(productLot, ProductLotDTO.class);
		int dbRs = productLotService.insertOne(dto);
		return "redirect:./lists";
	}
	
	
	@GetMapping(value="/update/{lot}")
	public String getProduct(@PathVariable("lot")String lot, ModelMap model)
	{
		ProductLotDTO dbRs = productLotService.getOne(lot);
		ProductLotBean bean = mapper.map(dbRs, ProductLotBean.class);
		model.addAttribute("product", bean);
		return "product-update";
	}
	
	@PostMapping(value="/update/doupdate")
	public String updateProduct(@ModelAttribute("product")ProductLotBean product)
	{
		ProductLotDTO dto=mapper.map(product, ProductLotDTO.class);
		productLotService.updateOne(dto);
		return "redirect:../lists";
	}
	
	@ModelAttribute("categories")
	public Map<String, String> getCategories()
	{
		List<CategoryDTO> dbRs = categoryService.getAll();
		HashMap<String, String> categories=new HashMap<>();
		for(CategoryDTO dto: dbRs)
		{
			categories.put(dto.getId(), dto.getName());
		}
		return categories;
	}
	
	@ModelAttribute("uom")
	public List<String> getUoms()
	{
		List<String> uoms = new ArrayList<>();
		uoms.add("KG");
		uoms.add("Bag");
		uoms.add("EA");
		uoms.add("Box");
		uoms.add("Bottle");
		return uoms;
	}
	
}
