package com.example.controller;

import com.example.dto.OkolnostiNezgodeBDTO;
import com.example.dto.OkolnostiNezgodeDTO;
import com.example.dto.UserDTO;
import com.example.dto.ZvanicniOrganiDTO;
import com.example.mapper.OkolnostiNezgodeBMapper;
import com.example.mapper.OkolnostiNezgodeMapper;
import com.example.mapper.UserMapper;
import com.example.mapper.ZvanicniOrganiMapper;
import com.example.model.*;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Controller
public class OkolnostiNezgodeController {

    @Autowired
    UserService userService;

    @Autowired
    OkolnostiNezgodeService okolnostiNezgodeService;

    @Autowired
    OkolnostiNezgodeBService bService;

    @Autowired
    ZvanicniOrganiService zvanicniOrganiService;

    @Autowired
    OkolnostiNezgodeMapper okolnostiNezgodeMapper;

    @Autowired
    ZvanicniOrganiMapper zvanicniOrganiMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OkolnostiNezgodeBMapper okolnostiNezgodeBMapper;

    @Autowired
    VozacNezIzvService vozacNezIzvService;

    @Autowired
    OkolnostiNezgodeBService okolnostiNezgodeBService;


    @RequestMapping(value = "/user/zvanicniOrgani/{idIzvm}", method = RequestMethod.GET)
    public ModelAndView zvanicniOrgani(@PathVariable Long idIzvm) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);
        ZvanicniOrganiDTO zvanicniOrganiDTO = null;

        VozacNezgodaManja manja = vozacNezIzvService.findManjaById(idIzvm);
        ZvanicniOrgani zo = zvanicniOrganiService.findByVozacNezgodaManja(manja);
        if (zo != null) {
            zvanicniOrganiDTO = zvanicniOrganiMapper.convertToDto(zo);
        } else {
            zvanicniOrganiDTO = new ZvanicniOrganiDTO();
        }

        boolean poslat = manja.isPoslat();
        modelAndView.addObject("userName", userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("userDTO", userDTO);
        modelAndView.addObject("idIzvm",idIzvm);
        modelAndView.addObject("poslat", poslat);
        modelAndView.addObject("zvanicniOrganiDTO",zvanicniOrganiDTO);
        modelAndView.setViewName("user/zvanicniOrgani");

        return modelAndView;
    }


    @RequestMapping(value = "/user/snimiZvanicneOrgane/{idIzvm}", method = RequestMethod.POST)
    public ModelAndView snimiZvanicneOrgane(@Valid @ModelAttribute ZvanicniOrganiDTO zvanicniOrganiDTO,
                                              BindingResult result,
                                              @PathVariable Long idIzvm,
                                              RedirectAttributes redirectAttributes) throws ParseException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        if(result.hasErrors()){
            ModelAndView m = new ModelAndView("redirect:/user/zvanicniOrgani/" +idIzvm);
            m.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
            m.addObject("zvanicniOrganiDTO",zvanicniOrganiDTO);
            m.setViewName("user/zvanicniOrgani");
            m.addObject("successMessage", "Imate grešku!");
            return m;
        }
        if (!result.hasErrors()) {
            ModelAndView m = new ModelAndView("redirect:/user/zvanicniOrgani/" +idIzvm);
            redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
            redirectAttributes.addFlashAttribute("successMessage", "Izmene uspešno sačuvane!");
            zvanicniOrganiDTO.setId(zvanicniOrganiDTO.getId());
            zvanicniOrganiDTO.setVersion(zvanicniOrganiDTO.getVersion());

            ZvanicniOrgani zvanicniOrgani = zvanicniOrganiMapper.convertToEntity(zvanicniOrganiDTO);
            //ZvanicniOrgani organi = zvanicniOrganiService.findZvanicniOrganiById(zvanicniOrgani.getId());

                zvanicniOrgani.setVozacNezgodaManja(vozacNezIzvService.findManjaById(idIzvm));
                zvanicniOrganiService.saveZvanicniOrgani(zvanicniOrgani);

            return m;
        }
        return new ModelAndView("redirect:/user/zvanicniOrgani/" +idIzvm);
    }

    @RequestMapping(value = "/user/okolnostiNezgode/{idIzvm}", method = RequestMethod.GET)
    public ModelAndView okolnostiNezgode(@PathVariable Long idIzvm) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);


        VozacNezgodaManja manja = vozacNezIzvService.findManjaById(idIzvm);
        OkolnostiNezgode a = okolnostiNezgodeService.findNezgodaByManja(manja);
        OkolnostiNezgodeB b = bService.findNezgodaBByManja(manja);

        OkolnostiNezgodeDTO okolnostiNezgodeDTO = null;
        OkolnostiNezgodeBDTO okolnostiNezgodeBDTO  = null;


        if (a!=null && b!=null) {
            okolnostiNezgodeDTO = okolnostiNezgodeMapper.convertToDto(a);
            okolnostiNezgodeBDTO = okolnostiNezgodeBMapper.convertToDto(b);
        } else {
            okolnostiNezgodeDTO = new OkolnostiNezgodeDTO();
            okolnostiNezgodeBDTO = new OkolnostiNezgodeBDTO();
        }

        boolean poslat = manja.isPoslat();

        modelAndView.addObject("userName", userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("userDTO",userDTO);
        modelAndView.addObject("okolnostiNezgodeDTO",okolnostiNezgodeDTO);
        modelAndView.addObject("okolnostiNezgodeBDTO",okolnostiNezgodeBDTO);
        modelAndView.addObject("poslat", poslat);
        modelAndView.addObject("idIzvm", idIzvm);
        modelAndView.setViewName("user/okolnostiNezgode");

        return modelAndView;
    }
    @RequestMapping(value = "/user/snimiOkolnostiNezgode/{idIzvm}", method = RequestMethod.POST)
    public ModelAndView snimiOkolnostiNezgode(@Valid @ModelAttribute OkolnostiNezgodeDTO okolnostiNezgodeDTO,
                                              BindingResult result,
                                              @Valid @ModelAttribute OkolnostiNezgodeBDTO okolnostiNezgodeBDTO,
                                              BindingResult result2,
                                              @PathVariable Long idIzvm,
                                              RedirectAttributes redirectAttributes) throws ParseException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        if(result.hasErrors() || result2.hasErrors()){
            ModelAndView m = new ModelAndView("redirect:/user/okolnostiNezgode/" +idIzvm);
            m.addObject("userName",user.getName() + " " + user.getLastName());
            m.addObject("okolnostiNezgodeDTO",okolnostiNezgodeDTO);
            m.addObject("okolnostiNezgodeBDTO",okolnostiNezgodeBDTO);
            m.setViewName("user/okolnostiNezgode");
            m.addObject("successMessage", "Imate grešku!");
            return m;
        }
        if (!result.hasErrors() && !result2.hasErrors()) {
            ModelAndView m = new ModelAndView("redirect:/user/okolnostiNezgode/"+ idIzvm);
            redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
            redirectAttributes.addFlashAttribute("successMessage", "Izmene uspešno sačuvane!");

            OkolnostiNezgode okolnostiNezgode = okolnostiNezgodeMapper.convertToEntity(okolnostiNezgodeDTO);
            okolnostiNezgode.setVozacNezgodaManja(vozacNezIzvService.findManjaById(idIzvm));
            okolnostiNezgodeService.updateOkolnostiNezgode(okolnostiNezgode);

            OkolnostiNezgodeB okolnostiNezgodeB = okolnostiNezgodeBMapper.convertToEntity(okolnostiNezgodeBDTO);
            okolnostiNezgodeB.setVozacNezgodaManja(vozacNezIzvService.findManjaById(idIzvm));
            okolnostiNezgodeBService.updateOkolnostiNezgodeB(okolnostiNezgodeB);
            return m;
        }
        return new ModelAndView("redirect:/user/okolnostiNezgode/" +idIzvm);
    }

}
