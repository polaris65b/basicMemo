package com.example.basicmemo.service;

import com.example.basicmemo.dto.MemoRequestDto;
import com.example.basicmemo.dto.MemoResponseDto;
import com.example.basicmemo.entity.Memo;
import com.example.basicmemo.repository.MemoRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepositroy memoRepositroy;

    // Create
    @Transactional
    public MemoResponseDto save(MemoRequestDto dto){
        Memo memo = new Memo(dto.getContent());
        Memo savedMemo = memoRepositroy.save(memo);
        return new MemoResponseDto(savedMemo.getId(), savedMemo.getContent());
    }

    // Read

    // 다건조회
    @Transactional(readOnly = true)
    public List<MemoResponseDto> findAll(){
        List<Memo> memos = memoRepositroy.findAll();

        List<MemoResponseDto> dtos = new ArrayList<>();
        for(Memo memo : memos){
            dtos.add(new MemoResponseDto(memo.getId(), memo.getContent()));
        }
        return dtos;
    }

    // 단건조회
    @Transactional(readOnly = true)
    public MemoResponseDto findById(Long id){
        Memo memo = memoRepositroy.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 메모가 없습니다.")
        );
        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    // update
    @Transactional
    public MemoResponseDto update(Long id, MemoRequestDto dto){
        Memo memo = memoRepositroy.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 메모가 없습니다.")
        );
        memo.update(dto.getContent());
        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    // delete
    @Transactional
    public void deleteById(Long id){
        if(!memoRepositroy.existsById(id)){
            throw new IllegalArgumentException("해당 id에 맞는 메모가 없습니다.");
        }
        memoRepositroy.deleteById(id);
    }
}
