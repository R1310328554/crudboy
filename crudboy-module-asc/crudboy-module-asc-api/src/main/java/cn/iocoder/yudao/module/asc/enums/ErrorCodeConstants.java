package cn.iocoder.yudao.module.asc.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Infra 错误码枚举类
 *
 * infra 系统，使用 1-001-000-000 段
 */
public interface ErrorCodeConstants {

// TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 预设提示词 TODO 补充编号 ==========
ErrorCode BOT_PROMPT_NOT_EXISTS = new ErrorCode(1000001, "预设提示词不存在");

// TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 后续主动沟通的询问方案 TODO 补充编号 ==========
ErrorCode AFTERWARDS_CALLBACK_CFG_NOT_EXISTS = new ErrorCode(1000001, "后续主动沟通的询问方案不存在");

// TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 机器人的基础配置 TODO 补充编号 ==========
ErrorCode BOT_BASE_CFG_NOT_EXISTS = new ErrorCode(1000001, "机器人的基础配置不存在");

// TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 单个聊天消息 TODO 补充编号 ==========
ErrorCode CHAT_MSG_NOT_EXISTS = new ErrorCode(1000001, "单个聊天消息不存在");

// TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 聊天的会话 TODO 补充编号 ==========
ErrorCode CHAT_SESSION_NOT_EXISTS = new ErrorCode(1000001, "聊天的会话不存在");

// TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 租户的token消耗情况 TODO 补充编号 ==========
ErrorCode CONSUMED_TOKENS_NOT_EXISTS = new ErrorCode(1000001, "租户的token消耗情况不存在");

// TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 终端用户 TODO 补充编号 ==========
ErrorCode END_USER_NOT_EXISTS = new ErrorCode(1000001, "终端用户不存在");

// TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 终端用户配置 TODO 补充编号 ==========
ErrorCode END_USER_CFG_NOT_EXISTS = new ErrorCode(1000001, "终端用户配置不存在");

// TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 百问百答训练 TODO 补充编号 ==========
ErrorCode FAQ_TRAINING_NOT_EXISTS = new ErrorCode(1000001, "百问百答训练不存在");

// TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 关键信息和文件链接匹配 TODO 补充编号 ==========
ErrorCode FILE_MATCH_CFG_NOT_EXISTS = new ErrorCode(1000001, "关键信息和文件链接匹配不存在");



    // TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 网站内容爬取训练 TODO 补充编号 ==========
    ErrorCode WEBSITE_TRAINING_NOT_EXISTS = new ErrorCode(1000001, "网站内容爬取训练不存在");

    // TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 文档训练 TODO 补充编号 ==========
    ErrorCode FILE_TRAINING_NOT_EXISTS = new ErrorCode(1000001, "文档训练不存在");

    // TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== GPT 专用的基础的配置 TODO 补充编号 ==========
    ErrorCode GPT_CFG_NOT_EXISTS = new ErrorCode(1000001, "GPT 专用的基础的配置不存在");

    // TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 终端平台即第三方平台配置 TODO 补充编号 ==========
    ErrorCode THIRD_PARTY_CFG_NOT_EXISTS = new ErrorCode(1000001, "终端平台即第三方平台配置不存在");

    // TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 客户信息收集 TODO 补充编号 ==========
    ErrorCode USER_INFO_COLLECTION_NOT_EXISTS = new ErrorCode(1000001, "客户信息收集不存在");

    // TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== WhatsApp 平台配置 TODO 补充编号 ==========
    ErrorCode WHATSAPP_CFG_NOT_EXISTS = new ErrorCode(1000001, "WhatsApp 平台配置不存在");

}
