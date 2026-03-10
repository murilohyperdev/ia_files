import { createBrowserRouter } from 'react-router-dom'

import { MainLayout } from '@/components/layout/MainLayout'
import { PrivateRoute } from './PrivateRoute'
import { LoginPage } from '@/pages/auth/LoginPage'
import { SignupPage } from '@/pages/auth/SignupPage'
import { HomePage } from '@/pages/home/HomePage'
import { ProfilePage } from '@/pages/profile/ProfilePage'
import { UserListPage } from '@/pages/admin/users/UserListPage'
import { UserFormPage } from '@/pages/admin/users/UserFormPage'
import { GroupListPage } from '@/pages/admin/groups/GroupListPage'
import { GroupFormPage } from '@/pages/admin/groups/GroupFormPage'
import { RuleListPage } from '@/pages/admin/rules/RuleListPage'

export const router = createBrowserRouter([
  {
    path: '/login',
    element: <LoginPage />,
  },
  {
    path: '/signup',
    element: <SignupPage />,
  },
  {
    path: '/',
    element: (
      <PrivateRoute>
        <MainLayout />
      </PrivateRoute>
    ),
    children: [
      {
        index: true,
        element: <HomePage />,
      },
      {
        path: 'profile',
        element: <ProfilePage />,
      },
      {
        path: 'admin/users',
        element: <UserListPage />,
      },
      {
        path: 'admin/users/new',
        element: <UserFormPage />,
      },
      {
        path: 'admin/users/:id',
        element: <UserFormPage />,
      },
      {
        path: 'admin/groups',
        element: <GroupListPage />,
      },
      {
        path: 'admin/groups/new',
        element: <GroupFormPage />,
      },
      {
        path: 'admin/groups/:id',
        element: <GroupFormPage />,
      },
      {
        path: 'admin/rules',
        element: <RuleListPage />,
      },
    ],
  },
])
