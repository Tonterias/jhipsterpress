import { element, by, ElementFinder } from 'protractor';

export class FollowComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-follow div table .btn-danger'));
    title = element.all(by.css('jhi-follow div h2#page-heading span')).first();

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

export class FollowUpdatePage {
    pageTitle = element(by.id('jhi-follow-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    creationDateInput = element(by.id('field_creationDate'));
    followedSelect = element(by.id('field_followed'));
    followingSelect = element(by.id('field_following'));
    cfollowedSelect = element(by.id('field_cfollowed'));
    cfollowingSelect = element(by.id('field_cfollowing'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCreationDateInput(creationDate) {
        await this.creationDateInput.sendKeys(creationDate);
    }

    async getCreationDateInput() {
        return this.creationDateInput.getAttribute('value');
    }

    async followedSelectLastOption() {
        await this.followedSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async followedSelectOption(option) {
        await this.followedSelect.sendKeys(option);
    }

    getFollowedSelect(): ElementFinder {
        return this.followedSelect;
    }

    async getFollowedSelectedOption() {
        return this.followedSelect.element(by.css('option:checked')).getText();
    }

    async followingSelectLastOption() {
        await this.followingSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async followingSelectOption(option) {
        await this.followingSelect.sendKeys(option);
    }

    getFollowingSelect(): ElementFinder {
        return this.followingSelect;
    }

    async getFollowingSelectedOption() {
        return this.followingSelect.element(by.css('option:checked')).getText();
    }

    async cfollowedSelectLastOption() {
        await this.cfollowedSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async cfollowedSelectOption(option) {
        await this.cfollowedSelect.sendKeys(option);
    }

    getCfollowedSelect(): ElementFinder {
        return this.cfollowedSelect;
    }

    async getCfollowedSelectedOption() {
        return this.cfollowedSelect.element(by.css('option:checked')).getText();
    }

    async cfollowingSelectLastOption() {
        await this.cfollowingSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async cfollowingSelectOption(option) {
        await this.cfollowingSelect.sendKeys(option);
    }

    getCfollowingSelect(): ElementFinder {
        return this.cfollowingSelect;
    }

    async getCfollowingSelectedOption() {
        return this.cfollowingSelect.element(by.css('option:checked')).getText();
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

export class FollowDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-follow-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-follow'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
