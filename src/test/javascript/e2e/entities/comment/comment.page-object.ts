import { element, by, ElementFinder } from 'protractor';

export class CommentComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-comment div table .btn-danger'));
    title = element.all(by.css('jhi-comment div h2#page-heading span')).first();

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

export class CommentUpdatePage {
    pageTitle = element(by.id('jhi-comment-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    creationDateInput = element(by.id('field_creationDate'));
    commentTextInput = element(by.id('field_commentText'));
    isOffensiveInput = element(by.id('field_isOffensive'));
    userSelect = element(by.id('field_user'));
    postSelect = element(by.id('field_post'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCreationDateInput(creationDate) {
        await this.creationDateInput.sendKeys(creationDate);
    }

    async getCreationDateInput() {
        return this.creationDateInput.getAttribute('value');
    }

    async setCommentTextInput(commentText) {
        await this.commentTextInput.sendKeys(commentText);
    }

    async getCommentTextInput() {
        return this.commentTextInput.getAttribute('value');
    }

    getIsOffensiveInput() {
        return this.isOffensiveInput;
    }

    async userSelectLastOption() {
        await this.userSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async userSelectOption(option) {
        await this.userSelect.sendKeys(option);
    }

    getUserSelect(): ElementFinder {
        return this.userSelect;
    }

    async getUserSelectedOption() {
        return this.userSelect.element(by.css('option:checked')).getText();
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

export class CommentDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-comment-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-comment'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
