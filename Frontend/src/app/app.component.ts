import {Component, OnInit} from '@angular/core';
import {User} from "./user.model";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
    public users: User[];

    ngOnInit(): void {
        this.setUsers();
    }

    private setUsers(): void {
        this.users = [
            new User(1, "Name1", "Surname1"),
            new User(2, "Name2", "Surname2"),
            new User(3, "Name3", "Surname3")];
    }
}
