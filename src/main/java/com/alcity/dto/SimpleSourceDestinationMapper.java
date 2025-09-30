package com.alcity.dto;


import com.alcity.dto.appmember.AppMemberDTO;
import com.alcity.dto.base.ClientTypeDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.ClientType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SimpleSourceDestinationMapper {
    AppMemberDTO sourceToDestination(AppMember appMember);

}
