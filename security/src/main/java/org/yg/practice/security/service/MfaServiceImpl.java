package org.yg.practice.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yg.practice.security.datas.dto.MfaDto;
import org.yg.practice.security.datas.dto.MfaInitDto;
import org.yg.practice.security.datas.dto.MfaProveDto;
import org.yg.practice.security.datas.entities.*;
import org.yg.practice.security.repositories.MfaRepository;


@Service
public class MfaServiceImpl implements MfaService {
    private final MfaRepository mfaRepository;

    @Autowired
    public MfaServiceImpl(MfaRepository mfaRepository){
        this.mfaRepository = mfaRepository;
    }

    @Override
    public MfaDto getMfa(String username){
        return new MfaDto(mfaRepository.findByUsername(username));
    }

    @Override
    public MfaProveDto getMfaSecretKey(String username){
        return new MfaProveDto(mfaRepository.findByUsername(username));
    }

    @Override
    public MfaInitDto setMfa(MfaInitDto mfaInitDto){
        mfaRepository.save(new Mfa(mfaInitDto));
        return mfaInitDto;
    }

    @Override
    public MfaDto setMfa(MfaDto mfaDto){
        mfaRepository.save(new Mfa(mfaDto));
        return mfaDto;
    }

    @Override
    public void deleteMfa(String username){
        mfaRepository.delete(mfaRepository.findByUsername(username));
    }



}
