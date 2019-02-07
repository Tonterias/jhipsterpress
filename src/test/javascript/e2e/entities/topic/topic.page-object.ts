import { element, by, ElementFinder } from 'protractor';

export class TopicComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-topic div table .btn-danger'));
    title = element.all(by.css('jhi-topic div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class TopicUpdatePage {
    pageTitle = element(by.id('jhi-topic-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    topicNameInput = element(by.id('field_topicName'));
    postSelect = element(by.id('field_post'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setTopicNameInput(topicName) {
        await this.topicNameInput.sendKeys(topicName);
    }

    async getTopicNameInput() {
        return this.topicNameInput.getAttribute('value');
    }

    async postSelectLastOption() {
        await this.postSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async postSelectOption(option) {
        await this.postSelect.sendKeys(option);
    }

    getPostSelect(): ElementFinder {
        return this.postSelect;
    }

    async getPostSelectedOption() {
        return this.postSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class TopicDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-topic-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-topic'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
