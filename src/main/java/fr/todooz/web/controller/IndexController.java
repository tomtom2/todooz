package fr.todooz.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.todooz.domain.Task;
import fr.todooz.service.TagCloudService;
import fr.todooz.service.TaskService;
import fr.todooz.util.IntervalUtils;
import fr.todooz.util.TagCloud;

@Controller
public class IndexController {

	@Inject
	private TaskService taskService;

	@Inject
	private TagCloudService tagCloudService;

	@ModelAttribute
	public TagCloud tagCloud() {
		return tagCloudService.buildTagCloud();
	}

	@RequestMapping({ "/", "/index" })
	public String index(Model model) {
		return page(model, taskService.findAll());
	}

	@RequestMapping("/search")
	public String search(String query, Model model) {
		return page(model, taskService.findByQuery(query));
	}

	@RequestMapping("/tag/{tag}")
	public String tag(@PathVariable String tag, Model model) {
		return page(model, taskService.findByTag(tag));
	}
	
	
	@RequestMapping("/today")
	public String today(String query, Model model) {
		return page(model, taskService.findByInterval(IntervalUtils.todayInterval()));
	}
	
	@RequestMapping("/tomorrow")
	public String tomorrow(String query, Model model) {
		return page(model, taskService.findByInterval(IntervalUtils.tomorrowInterval()));
	}
	

	public String page(Model model, List<Task> tasks) {
		model.addAttribute("tasks", tasks);

		return "index";
	}

	
	@PostConstruct
	public void bootstrap() {

		if (taskService.count() == 0) {
		      
			Task task1 = new Task();
			task1.setDate(new Date("12/01/2012"));
			task1.setTitle("Read Effective Java");
			task1.setText("Read Effective Java before it's too late");
			task1.setTags("tag1,tag2,JEE");

			Task task2 = new Task();
			task2.setDate(new Date("12/03/2012"));
			task2.setTitle("Finir les tutos MC");
			task2.setText("Relire et poursuivre les tutoriels de la Masterclass");
			task2.setTags("spring,JEE,autres...");

			Task task3 = new Task();
			task3.setDate(new Date());
			task3.setTitle("Gala");
			task3.setText("Faire le ménage et préparer l'apéro");
			task3.setTags("balais,chips,cocktails");

			taskService.save(task1);
			taskService.save(task2);
			taskService.save(task3);
		}
	}
}