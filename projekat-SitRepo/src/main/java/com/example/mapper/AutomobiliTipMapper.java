package com.example.mapper;

import com.example.dto.AutomobiliTipDTO;
import com.example.model.AutomobiliTip;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class AutomobiliTipMapper {

    @Autowired
    private ModelMapper modelMapper;


    public List<AutomobiliTipDTO> convertListToDto(List<AutomobiliTip> automobiliTips) {
        List<AutomobiliTipDTO> automobiliTipDTOS = new ArrayList<AutomobiliTipDTO>();
        automobiliTipDTOS = automobiliTips.stream().map(user -> modelMapper.map(user, AutomobiliTipDTO.class))
                .collect(Collectors.toList());
        return automobiliTipDTOS;
    }

    public List<AutomobiliTip> convertListToEntity(List<AutomobiliTipDTO> automobiliTipDTOS) throws ParseException {
        List<AutomobiliTip> automobiliTips = new ArrayList<AutomobiliTip>();
        automobiliTips = automobiliTipDTOS.stream().map(user -> modelMapper.map(user, AutomobiliTip.class))
                .collect(Collectors.toList());
        return automobiliTips;
    }

    public AutomobiliTipDTO convertToDto(AutomobiliTip automobiliTip) {
        AutomobiliTipDTO automobiliTipDTO = modelMapper.map(automobiliTip, AutomobiliTipDTO.class);
        return automobiliTipDTO;
    }

    public AutomobiliTip convertToEntity(AutomobiliTipDTO automobiliTipDTO) throws ParseException {
        AutomobiliTip automobiliTip = modelMapper.map(automobiliTipDTO, AutomobiliTip.class);
        return automobiliTip;
    }
}
