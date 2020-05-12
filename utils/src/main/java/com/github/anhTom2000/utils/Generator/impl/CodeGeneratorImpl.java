package com.github.anhTom2000.utils.Generator.impl;

import com.github.anhTom2000.utils.Generator.CodeGenerator;

import java.util.Random;

/**
 * @Description : TODO  验证码生成器实现类
 * @Author : Weleness
 * @Date : 2020/05/03
 */
public class CodeGeneratorImpl implements CodeGenerator {
    private static final String CODE_SOURCE = "1234567890";

    private static final Random RANDOM = new Random();

    private static final Integer CODE_LENGTH = 6;

    /**
     * @Method
     * Description:
     *  生成6位数的验证码
     * @Author weleness
     *
     * @Return
     */
    @Override
    public String generateCode() {
        char[] code = new char[CODE_LENGTH];
        for (Integer i = 0; i < CODE_LENGTH; i++) {
            code[i] = CODE_SOURCE.charAt(RANDOM.nextInt(CODE_LENGTH));
        }
        return String.valueOf(code);
    }
}
