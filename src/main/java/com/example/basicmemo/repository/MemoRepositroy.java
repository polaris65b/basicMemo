package com.example.basicmemo.repository;

import com.example.basicmemo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepositroy extends JpaRepository<Memo, Long> {
}
