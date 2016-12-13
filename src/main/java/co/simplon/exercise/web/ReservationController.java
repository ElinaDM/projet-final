package co.simplon.exercise.web;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import co.simplon.exercise.core.model.Classroom;
import co.simplon.exercise.core.model.Laptop;
import co.simplon.exercise.core.model.User;
import co.simplon.exercise.core.service.ClassroomService;
import co.simplon.exercise.core.service.LaptopService;
import co.simplon.exercise.core.service.UserService;

import co.simplon.exercise.core.service.mailing.EmailAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.simplon.exercise.core.model.Reservation;
import co.simplon.exercise.core.service.ReservationService;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
	private LaptopService laptopService;

    @Autowired
	private ClassroomService classroomService;

    @Autowired
	private EmailAPI emailAPI;
	
	@RequestMapping
	public ModelAndView showReservations(ModelMap model)
	{
		model.addAttribute("reservations", reservationService.getAll());
		
		return new ModelAndView("reservation/reservations", model);
	}
	
	/**
	 * Display the form to add a reservation
	 *
	 * @return
	 */
	@RequestMapping(value = "laptop/formAdd", method = RequestMethod.GET)
	public ModelAndView getFormAddLaptopReservation(ModelMap model) {

		return new ModelAndView("reservation/search", model);

	}

	@RequestMapping(value = "resources/search")
	public ModelAndView search(
						@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookingDate,
						@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
						@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
						ModelMap model,
						final RedirectAttributes redirectAttribute
						)
	{
	    // Get the list of available items for a given date
		List<Laptop>availableLaptops   = laptopService.getAvailableLaptops(bookingDate, startTime, endTime);
		List<Classroom> availableRooms = classroomService.getAvailableRooms(bookingDate, startTime, endTime);

		if (availableLaptops.size() == 0 || availableRooms.size() == 0) {
			redirectAttribute.addFlashAttribute("info", "Aucun élémenys correspond à votre recherche !");
			return new ModelAndView("redirect:/reservations/resources/search");
		}
		else {
			// Get search params for booking
			Map<String, Object> searchParams = new HashMap<>();
			searchParams.put("bookingDate", bookingDate);
			searchParams.put("startTime", startTime);
			searchParams.put("endTime", endTime);
			model.addAllAttributes(searchParams);

			model.addAttribute("availableLaptops", availableLaptops);
			model.addAttribute("availableRooms", availableRooms);
			return new ModelAndView("reservation/resource-booking");
		}
	}


	@RequestMapping(path = "/resource/add", method = RequestMethod.GET)
	public ModelAndView addReservation(@RequestParam(name = "laptopId") Integer laptopId,
									   @RequestParam(name = "roomId") Integer roomId,
							           @RequestParam(name = "bookingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate bookingDate,
									   @RequestParam(name = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)LocalTime startTime,
									   @RequestParam(name = "endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)LocalTime endTime,
	                                   ModelMap model,
									   final RedirectAttributes redirectAttribute)
	{
		// get Laptop objet from id
		Laptop bookedLaptop =laptopService.findById(laptopId);

		// Récupérer la salle sélectionnée
		Classroom bookedRoom = classroomService.findById(roomId);

        // Get User from context
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findOneByEmail(email);
        if (currentUser == null) {
        	redirectAttribute.addFlashAttribute("", "Il faut se connecter pour pouvoir réserver");
        	return new ModelAndView("redirect:/login");
		}

        // Get the date of creation
		Date createdAt = new Date();
        // Create a reservation
		Reservation res = new Reservation(createdAt, bookingDate, startTime, endTime, currentUser,bookedLaptop, bookedRoom );

		// Add created resrvation to DB
		reservationService.addOrUpdate(res);

		String to ="simplon.company@gmail.com";
		String subject = "Confirmation de réservation";
		String content = " Bonjour " + currentUser.getSurname() + " vous avez éffectué une réservation pour "+bookingDate ;
		emailAPI.sendEmail(email, to, subject, content);

		// Redirection to reservations list with a flash message
		redirectAttribute.addFlashAttribute("message", "Réservation ajoutée avec succès !");
		ModelAndView mav = new ModelAndView("redirect:/reservations");
		mav.getModelMap().addAllAttributes(model);
		return mav;

	}


	// Afficher le formulaire pour modifier une réservation
	@RequestMapping(path= "/updateForm")
	public ModelAndView getUpdateForm(@RequestParam Integer id, ModelMap model)
	{
		model.addAttribute("bookToUpdate", reservationService.findById(id));
		return new ModelAndView("updateReservationForm", model);
	}

	// Modifier une réservation
	@RequestMapping(path = "/update")
	public ModelAndView updateReservation() {

		return new ModelAndView("");
	}
	
	@RequestMapping(path = "/delete")
	public ModelAndView  deleteReservation(@RequestParam Integer id, ModelMap model)
	{
		reservationService.delete(id);
		return new ModelAndView("redirect:/reservations");
		
	}
	
}

