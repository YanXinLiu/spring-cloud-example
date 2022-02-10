package com.yanxin.workflow.service;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2022-01-20 00:11
 */
@Service
public class WorkflowService {

    private final Logger logger = LoggerFactory.getLogger(WorkflowService.class);

    /*ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    RepositoryService repositoryService = processEngine.getRepositoryService();
    RuntimeService runtimeService = processEngine.getRuntimeService();
    TaskService taskService = processEngine.getTaskService();
    IdentityService identityService = processEngine.getIdentityService();
    FormService formService = processEngine.getFormService();
    HistoryService historyService = processEngine.getHistoryService();
    ManagementService managementService = processEngine.getManagementService();
    FilterService filterService = processEngine.getFilterService();
    ExternalTaskService externalTaskService = processEngine.getExternalTaskService();
    CaseService caseService = processEngine.getCaseService();
    DecisionService decisionService = processEngine.getDecisionService();

    public WorkflowService(ProcessEngine processEngine, RepositoryService repositoryService,
                           RuntimeService runtimeService, TaskService taskService,
                           IdentityService identityService, FormService formService,
                           HistoryService historyService, ManagementService managementService,
                           FilterService filterService, ExternalTaskService externalTaskService,
                           CaseService caseService, DecisionService decisionService) {
        this.processEngine = processEngine;
        this.repositoryService = repositoryService;
        this.runtimeService = runtimeService;
        this.taskService = taskService;
        this.identityService = identityService;
        this.formService = formService;
        this.historyService = historyService;
        this.managementService = managementService;
        this.filterService = filterService;
        this.externalTaskService = externalTaskService;
        this.caseService = caseService;
        this.decisionService = decisionService;
    }*/

    @Autowired
    private RepositoryService repositoryService;

    @Bean
    public JavaDelegate sayHelloDelegate() {
        return execution -> logger.info("Hello!");
    }

    @Bean
    public JavaDelegate doActionDelegate() {
        return execution -> logger.info("do action!");
    }

    @EventListener
    public void notify(final PostDeployEvent unused) {
        final ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("Sample").singleResult();
        logger.info("Found deployed process: {}", processDefinition);
        Assert.notNull(processDefinition, "process 'Sample' should be deployed!");
    }

    /*@EventListener
    private void processPostDeploy(PostDeployEvent event) {
        runtimeService.startProcessInstanceByKey("loan-approval");
    }*/
}
